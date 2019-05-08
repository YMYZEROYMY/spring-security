package com.example.security.entity;

import java.io.Serializable;

public class Movie implements Serializable {
    private String name;
    private String info;
    private String path;

    public Movie() {
        super();
    }

    public Movie(String name, String info, String path) {
        this.name = name;
        this.info = info;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
