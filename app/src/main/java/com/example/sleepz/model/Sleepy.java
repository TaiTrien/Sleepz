package com.example.sleepz.model;

import java.sql.Date;
import java.sql.Time;

public class Sleepy {
    private String sleepTime;
    private String wakeTime;

    public Sleepy(String sleepTime, String wakeTime) {
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
    }

    public Sleepy() {
        this.wakeTime = wakeTime;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }
}
