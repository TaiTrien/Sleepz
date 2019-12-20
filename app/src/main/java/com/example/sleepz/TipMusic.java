package com.example.sleepz;

public class TipMusic {
    private String title;
    private int file;

    public TipMusic (String Title, int File){
        title = Title;
        file = File;
    }

    public String getTitle(){
        return title;
    }
    public int getFile(){
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
