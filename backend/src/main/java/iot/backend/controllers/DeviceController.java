package iot.backend.controllers;

import iot.backend.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping(path = "/device/exchange/{id}/{data}")
    public @ResponseBody String exchange(@PathVariable("id") String id, @PathVariable("data") String data) {
        // TODO HANDLE AES CRYPTO
        return deviceService.exchange(id, data);
    }
}
