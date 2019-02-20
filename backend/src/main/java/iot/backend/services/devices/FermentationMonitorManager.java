package iot.backend.services.devices;

import iot.backend.data.DeviceType;

public class FermentationMonitorManager extends DeviceManager {

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.FermentationMonitor;
    }

    @Override
    public String process(String status, String newData) throws Exception {
        String[] params = newData.split(":");
        assureParamsCount(params, 1);
        int action = Integer.parseInt(params[PARAM_ACTION]);
        if (action == PARAM_ACTION_SEND_DATA) {
            // do nothing, this is a regular sensor data request
        }

        return status;
    }
}
