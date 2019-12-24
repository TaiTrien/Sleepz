package com.example.sleepz;

import java.sql.Time;

public class TIMESLEEP {
    private String sleeptime;
    private String waketime;

    public String getSleeptime() { return sleeptime; }
    public void setSleeptime(String sleeptime)
    {
        this.sleeptime=sleeptime;
    }
    public String getWaketime() { return waketime; }
    public void setWaketime(String waketime) { this.waketime=waketime; }
}
