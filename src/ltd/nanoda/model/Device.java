package ltd.nanoda.model;

public class Device {
    String battery;
    String online;

    public Device(String online) {
        this.online = online;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}
