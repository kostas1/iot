package iot.backend.services.devices;

import iot.backend.data.DeviceType;

public abstract class DeviceManager {

    protected static final int PARAM_ACTION = 0;
    protected static final int PARAM_ACTION_SEND_DATA = 1;

    protected static final int STATUS_PARAM_FREQUENCY = 1;
    protected static final int STATUS_PARAM_SLEEPWAKE = 2;
    protected static final int STATUS_PARAM_SLEEPWAKE_AWAKE = 1;
    protected static final int STATUS_PARAM_SLEEPWAKE_ASLEEP = 0;

    public abstract DeviceType getDeviceType();
    public abstract String process(String currentStatus, String newData) throws Exception;

    protected void assureParamsCount(String[] data, int count) throws Exception {
        if (data.length < count) {
            throw new Exception(String.format("Must have at least %s parameter in data set '%s'", count, data));
        }
    }

    public void setFrequencyMillis(String[] status, int millis) {
        status[STATUS_PARAM_FREQUENCY] = String.valueOf(millis);
    }

    public void putToSleep(String[] status) {
        status[STATUS_PARAM_SLEEPWAKE] = String.valueOf(STATUS_PARAM_SLEEPWAKE_ASLEEP);
    }

    public void putToAwake(String[] status) {
        status[STATUS_PARAM_SLEEPWAKE] = String.valueOf(STATUS_PARAM_SLEEPWAKE_AWAKE);
    }
}
