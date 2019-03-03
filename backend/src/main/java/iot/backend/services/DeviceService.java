package iot.backend.services;

import iot.backend.data.*;
import iot.backend.services.devices.DeviceManager;
import iot.backend.services.devices.FermentationMonitorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class DeviceService {

    Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    DeviceDataRepository deviceDataRepository;

    @Autowired
    DeviceRepository deviceRepository;

    public String exchange(String identifier, String status) {
//        logger.info("{}, {}", identifier, status);
        Optional<Device> optionalDevice = deviceRepository.findByIdentifier(identifier);
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            deviceDataRepository.save(new DeviceData(device.getId(), status));
            try {
                device.setStatus(processData(device, status));
                deviceRepository.save(device);
                return device.getStatus();
            } catch (Exception e) {
                logger.error("Error processing data: {}", e.getMessage());
                return "failed to processData: " + e.getMessage();
            }
        } else {
            return "device not found";
        }
    }

    private String processData(Device device, String status) throws Exception {
        DeviceManager manager = identifyManager(device);
        String newStatus = manager.process(status);
//        logger.info("Processed for device '{}' status '{}', last status '{}', new status '{}'", device.getIdentifier(), status, device.getStatus(), newStatus);
        return newStatus;
    }

    private DeviceManager identifyManager(Device device) throws Exception {
        switch (device.getType()) {
            case FermentationMonitor:
                return new FermentationMonitorManager(device);
            default:
                throw new Exception("Cannot identify DeviceManager for type " + device.getType().name());
        }
    }
}
