package com.example.patserfelices.instagram;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Data implements Serializable {

    @JsonProperty("data")
    private List<Photo> data;

    public List<Photo> getData() {
        return data;
    }

    public void setData(List<Photo> data) {
        this.data = data;
    }

    public List<String> getLinks() {
        return data.stream().map(Photo::getLink).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }
}
