package org.jahia.modules.examples.springmvc;

import java.io.Serializable;

/**
 * Created by Gramovich_V on 20.07.2017.
 */
public class Address implements Serializable {

    private  String city;

    private  int   id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
