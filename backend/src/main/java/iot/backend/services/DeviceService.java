package iot.backend.services;

import iot.backend.data.Device;
import iot.backend.data.DeviceData;
import iot.backend.data.DeviceDataRepository;
import iot.backend.data.DeviceRepository;
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

    public String get(String id) {
        logger.info("{}", id);
        return "get ok " + id;
    }

    public String submit(String id, String data) {
        logger.info("{}, {}", id, data);
        Optional<Device> device = deviceRepository.findByDeviceIdentifier(id);
        if (device.isPresent()) {
            deviceDataRepository.save(new DeviceData());
            return "submit ok " + id + data;
        } else {
            return "submit not ok " + id + data;
        }
    }
}
