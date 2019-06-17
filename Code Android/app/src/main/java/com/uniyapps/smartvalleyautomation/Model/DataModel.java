package com.uniyapps.smartvalleyautomation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("sensor")
    @Expose
    private Integer sensor;
    @SerializedName("water")
    @Expose
    private Integer water;
    @SerializedName("motor")
    @Expose
    private Integer motor;
    @SerializedName("auto")
    @Expose
    private Integer auto;
    @SerializedName("led")
    @Expose
    private Integer led;

    public DataModel(DataBoard dataBoard) {
    }

    public Integer getSensor() {
        return sensor;
    }

    public void setSensor(Integer sensor) {
        this.sensor = sensor;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public Integer getMotor() {
        return motor;
    }

    public void setMotor(Integer motor) {
        this.motor = motor;
    }

    public Integer getAuto() {
        return auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }

    public Integer getLed() {
        return led;
    }

    public void setLed(Integer led) {
        this.led = led;
    }


}


