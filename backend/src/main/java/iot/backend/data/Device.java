package iot.backend.data;

import javax.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String deviceIdentifier;

    public Device() {

    }

    public Device(String deviceIdentifier) {
        this.deviceIdentifier = deviceIdentifier;
    }
}
