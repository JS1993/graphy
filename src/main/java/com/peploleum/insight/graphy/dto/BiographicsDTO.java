package com.peploleum.insight.graphy.dto;

/**
 * Created by nicmir on 10/01/2019.
 */
public class BiographicsDTO {
    private String name;

    private String idMongo;

    public BiographicsDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdMongo() {
        return idMongo;
    }

    public void setIdMongo(String idMongo) {
        this.idMongo = idMongo;
    }
}