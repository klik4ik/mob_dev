package com.example.isinyavskiy.lab5.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by RubtsovM on 18.04.2018.
 */
//id (int, optional): The unique ID of the character resource.,
//name (string, optional): The name of the character.,
//description (string, optional): A short bio or description of the character.,
//modified (Date, optional): The date the resource was most recently modified.,
//resourceURI (string, optional): The canonical URL identifier for this resource.,
//urls (Array[Url], optional): A set of public web site URLs for the resource.,
//thumbnail (Image, optional): The representative image for this character.,
//comics (ComicList, optional): A resource list containing comics which feature this character.,
//stories (StoryList, optional): A resource list of stories in which this character appears.,
//events (EventList, optional): A resource list of events in which this character appears.,
//series (SeriesList, optional): A resource list of series in which this character appears.
public class Character implements Serializable{
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("thumbnail")
    private Image thumbnail;

    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }

    public void setDescription(String description) { this.description = description; }

    public void setName(String name) {
        this.name = name;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }
}