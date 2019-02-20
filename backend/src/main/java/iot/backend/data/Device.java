package iot.backend.data;

import javax.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Column
    private String identifier;

    @Column
    private String status;

    public Device() {

    }

    public Device(String identifier, String status, DeviceType type) {
        this.identifier = identifier;
        this.status = status;
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public DeviceType getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }
}
