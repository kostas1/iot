package iot.backend.controllers;

import iot.backend.services.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceController {

    Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    DeviceService deviceService;

    @RequestMapping(path = "/device/exchange/{identifier}/{status}")
    public @ResponseBody String exchange(@PathVariable("identifier") String identifier, @PathVariable("status") String status) {
        // TODO HANDLE AES CRYPTO
        logger.info("identifier='{}', status='{}'", identifier, status);
        return deviceService.exchange(identifier, status);
    }
}
