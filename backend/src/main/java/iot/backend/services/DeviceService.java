package iot.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class DeviceService {

    Logger logger = LoggerFactory.getLogger(DeviceService.class);

    public String get(String id) {
        logger.info("{}", id);
        return "get ok " + id;
    }

    public String submit(String id, String data) {
        logger.info("{}, {}", id, data);
        return "submit ok " + id + data;
    }
}
