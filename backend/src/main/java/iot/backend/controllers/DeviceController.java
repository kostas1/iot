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

    @RequestMapping(path = "/device/get/{id}")
    public @ResponseBody String get(@PathVariable("id") String id) {
        return deviceService.get(id);
    }

    @RequestMapping(path = "/device/submit/{id}/{data}")
    public @ResponseBody String submit(@PathVariable("id") String id, @PathVariable("data") String data) {
        return deviceService.submit(id, data);
    }
}
