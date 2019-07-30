package com.example.patserfelices.instagram;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Photo implements Serializable {

    @JsonProperty("link")
    private String link;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "link='" + link + '\'' +
                '}';
    }
}
