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

    public String exchange(String id, String data) {
        logger.info("{}, {}", id, data);
        Optional<Device> optionalDevice = deviceRepository.findByIdentifier(id);
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            deviceDataRepository.save(new DeviceData(device.getId(), data));
            try {
                device.setStatus(processData(device, data));
                return device.getStatus();
            } catch (Exception e) {
                logger.error("Error processing data: {}", e.getMessage());
                return "failed to processData: " + e.getMessage();
            }
        } else {
            return "device not found";
        }
    }

    private String processData(Device device, String data) throws Exception {
        DeviceManager manager = identifyManager(device.getType());
        String newStatus = manager.process(device.getStatus(), data);
        logger.info("Processed for device '{}' data '{}', last status '{}', new status '{}'", device.getIdentifier(), data, device.getStatus(), newStatus);
        return newStatus;
    }

    private DeviceManager identifyManager(DeviceType type) throws Exception {
        switch (type) {
            case FermentationMonitor:
                return new FermentationMonitorManager();
            default:
                throw new Exception("Cannot identify DeviceManager for type " + type.name());
        }
    }
}
