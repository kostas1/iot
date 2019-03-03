package iot.backend.services.devices;

import iot.backend.data.Device;

public class FermentationMonitorManager extends DeviceManager {

    protected static final int STATUS_PARAM_TEMPERATURE = 5;
    protected static final int STATUS_PARAM_BATTERYVOLTAGE = 6;
    protected static final int STATUS_PARAM_X = 7;
    protected static final int STATUS_PARAM_Y = 8;
    protected static final int STATUS_PARAM_Z = 9;

    public FermentationMonitorManager(Device device) {
        super(device);
    }

    @Override
    public String process(String data) throws Exception {
        String[] params = data.split(":"); // todo put split regex into configs
        assureParamsCount(params, 1);
        int action = Integer.parseInt(params[PARAM_ACTION]);
        if (action == PARAM_ACTION_SEND_DATA) {
            // do nothing, this is a regular sensor data request
            // for now just set this as new status for device
            this.device.setStatus(data);
        }

        return this.device.getStatus();
    }

    public void setTemperature(double temperature) {
        this.device.setStatusParameter(STATUS_PARAM_TEMPERATURE, doubleToString(temperature));
    }

    public double getTemperature() {
        return stringToDouble(this.device.getStatusParameter(STATUS_PARAM_TEMPERATURE));
    }

    public void setBatteryVoltage(double voltage) {
        this.device.setStatusParameter(STATUS_PARAM_BATTERYVOLTAGE, doubleToString(voltage));
    }

    public double getBatteryVoltage() {
        return stringToDouble(this.device.getStatusParameter(STATUS_PARAM_BATTERYVOLTAGE));
    }

    public void setX(double x) {
        this.device.setStatusParameter(STATUS_PARAM_X, doubleToString(x));
    }

    public double getX() {
        return stringToDouble(this.device.getStatusParameter(STATUS_PARAM_X));
    }

    public void setY(double y) {
        this.device.setStatusParameter(STATUS_PARAM_Y, doubleToString(y));
    }

    public double getY() {
        return stringToDouble(this.device.getStatusParameter(STATUS_PARAM_Y));
    }

    public void setZ(double z) {
        this.device.setStatusParameter(STATUS_PARAM_Z, doubleToString(z));
    }

    public double getZ() {
        return stringToDouble(this.device.getStatusParameter(STATUS_PARAM_Z));
    }
}
