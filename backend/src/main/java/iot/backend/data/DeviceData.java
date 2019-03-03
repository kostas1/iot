package iot.backend.data;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DeviceData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long deviceId;

    @Column
    private String status;

    @Column
    private long received;

    public DeviceData() {
    }

    public DeviceData(Long deviceId, String status) {
        this.deviceId = deviceId;
        this.status = status;
        this.received = new Date().getTime();
    }
}
