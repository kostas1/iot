package iot.backend.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Long> {

    Optional<Device> findByDeviceIdentifier(String deviceIdentifier);
}
