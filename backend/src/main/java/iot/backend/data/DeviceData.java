package iot.backend.data;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DeviceData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long deviceId;

    @Column
    private String data;

    @Column
    private Date received;

    public DeviceData() {
    }

    public DeviceData(Long deviceId, String data) {
        this.deviceId = deviceId;
        this.data = data;
        this.received = new Date();
    }
}
