package model;

/**
 * Created by ahmad on 20/12/2016.
 */
public class CurrentCondition {
    private int weaterId;
    private String condition;
    private String description;
    private String icon;
    private float pressure;
    private float humadity;
    private float maxTemp;
    private float minTemp;
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getWeaterId() {
        return weaterId;
    }

    public void setWeaterId(int weaterId) {
        this.weaterId = weaterId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumadity() {
        return humadity;
    }

    public void setHumadity(float humadity) {
        this.humadity = humadity;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }
}
