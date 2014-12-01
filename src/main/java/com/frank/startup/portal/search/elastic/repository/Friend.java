package com.frank.startup.portal.search.elastic.repository;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by FrankWong on 12/1/14.

@Document( indexName = "person" , type = "friend")
 */
public class Friend {

   // @Field(type = FieldType.String, store = true, indexAnalyzer = "ik", searchAnalyzer = "ik")
    private String name;

   // @Field(type = FieldType.String, store = true, indexAnalyzer = "ik", searchAnalyzer = "ik")
    private String address;

  //  @Field(type = FieldType.Integer, store = true, indexAnalyzer = "ik", searchAnalyzer = "ik")
    private int age;

   // @Field(store = true,type = FieldType.Object)
    private GeoPoint homePoint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GeoPoint getHomePoint() {
        return homePoint;
    }

    public void setHomePoint(GeoPoint homePoint) {
        this.homePoint = homePoint;
    }
}
