package com.mapcamp.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue
    private String store_id;

    private String lat;
    
    private String lon;
    
    private String name;
    
    private String price;
    
    private String time;

    private String url;

    public String getStoreId() {
        return store_id;
    }

    public void setStoreId(String store_id) {
        this.store_id = store_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
    
    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
