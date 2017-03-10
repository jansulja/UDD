package com.ftn.jan.util;

import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location =  ResourceBundle.getBundle("index").getString("docs");// "D:\\UDD_Storage";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
