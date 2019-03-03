package iot.backend.services.simulators;

import iot.backend.data.Device;
import iot.backend.data.DeviceDataRepository;
import iot.backend.data.DeviceRepository;
import iot.backend.data.DeviceType;
import iot.backend.services.DeviceService;
import iot.backend.services.devices.FermentationMonitorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class FermentationMonitorSimulator {

    @Autowired
    DeviceDataRepository deviceDataRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceService deviceService;

    @Scheduled(fixedRate = 1000)
    public void tick() {
        Random random = new Random();
        for (Device monitor: deviceRepository.findByType(DeviceType.FermentationMonitor)) {
            FermentationMonitorManager manager = new FermentationMonitorManager(monitor);
            manager.setTemperature(manager.getTemperature() + random.nextDouble() - 0.5);
            manager.setBatteryVoltage(manager.getBatteryVoltage() - 0.01);
            manager.setFrequencyMillis(60 * 60 * 1000);
            manager.setX(manager.getX() + 0.0091);
            manager.setY(manager.getY() + 0.0017);
            manager.setZ(manager.getZ() - 0.001);

            if (manager.getBatteryVoltage() < 1.5) {
                manager.setBatteryVoltage(3.3);
            }
            if (manager.getTemperature() < 10) {
                manager.setTemperature(23);
            }
            if (manager.getX() > 1) {
                manager.setX(0.1);
            }
            if (manager.getY() > 1) {
                manager.setY(0.1);
            }
            if (manager.getZ() < 0.1) {
                manager.setZ(1);
            }

            deviceService.exchange(monitor.getIdentifier(), manager.getDeviceStatus());
        }
    }
}
