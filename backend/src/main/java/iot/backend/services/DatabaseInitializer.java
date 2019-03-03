package iot.backend.services;

import iot.backend.data.Device;
import iot.backend.data.DeviceRepository;
import iot.backend.data.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    @Autowired
    DeviceRepository deviceRepository;

    @EventListener
    public void seedDatabase(ContextRefreshedEvent event) {
//        deviceRepository.save(new Device("1", "1:1", DeviceType.FermentationMonitor));
    }
}
