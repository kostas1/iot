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

    public void setStatusParameter(int index, String value) {
        // TODO put split regex into configs and consider splitting in constructor/setStatus for faster operations
        String[] split = this.status.split(":");
        split[index] = value;
        this.status = String.join(":", split);
    }

    public String getStatusParameter(int index) {
        return this.status.split(":")[index]; // TODO put split regex into configs and consider splitting in constructor/setStatus for faster operations
    }
}
