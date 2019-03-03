package iot.backend.services.devices;

import iot.backend.data.Device;
import iot.backend.data.DeviceType;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public abstract class DeviceManager {

    private DecimalFormat decimalFormat;

    protected static final int PARAM_ACTION = 0;
    protected static final int PARAM_ACTION_SEND_DATA = 1;

    protected static final int STATUS_PARAM_FREQUENCY = 1;
    protected static final int STATUS_PARAM_SLEEPWAKE = 2;
    protected static final int STATUS_PARAM_SLEEPWAKE_AWAKE = 1;
    protected static final int STATUS_PARAM_SLEEPWAKE_ASLEEP = 0;

    protected Device device;

    public DeviceManager(Device device) {
        this.device = device;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        this.decimalFormat = new DecimalFormat("########.########", symbols);
    }

    protected String doubleToString(double d) {
        return this.decimalFormat.format(d);
    }

    protected double stringToDouble(String s) {
        try {
            return this.decimalFormat.parse(s).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public abstract String process(String data) throws Exception;

    protected void assureParamsCount(String[] data, int count) throws Exception {
        if (data.length < count) {
            throw new Exception(String.format("Must have at least %s parameter in data set '%s'", count, data));
        }
    }

    public void setFrequencyMillis(int millis) {
        this.device.setStatusParameter(STATUS_PARAM_FREQUENCY, String.valueOf(millis));
    }

    public void putToSleep() {
        this.device.setStatusParameter(STATUS_PARAM_SLEEPWAKE, String.valueOf(STATUS_PARAM_SLEEPWAKE_ASLEEP));
    }

    public void putToAwake() {
        this.device.setStatusParameter(STATUS_PARAM_SLEEPWAKE, String.valueOf(STATUS_PARAM_SLEEPWAKE_AWAKE));
    }

    public String getDeviceStatus() {
        return this.device.getStatus();
    }
}
