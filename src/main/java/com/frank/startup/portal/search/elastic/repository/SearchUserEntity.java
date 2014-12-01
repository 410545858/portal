/**
 * 
 */
package com.frank.startup.portal.search.elastic.repository;

import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.search.aggregations.support.format.ValueParser;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author frankwong
 *
 */
@Document( indexName = "person" , type = "user")
public class SearchUserEntity {

	@Id
	private String id;

	@Field( type = FieldType.String ,index = FieldIndex.not_analyzed,store = true)
	private String name;

	@Field(type = FieldType.Integer,store=true)
	private int age;

	@Field(type = FieldType.String,indexAnalyzer = "ik",searchAnalyzer = "ik",store=true)
	private String address;

	@Field(type=FieldType.Boolean,store=true)
	private boolean gender;

	@Field(type=FieldType.Date,format = DateFormat.date_hour_minute_second_millis,store = true)
	private Date birthDay;

	//由于FieldType 不包含GEO_type,所以只能按照官方http://www.elasticsearch.org/guide/en/elasticsearch/reference/current/mapping-geo-point-type.html
	//String格式处理，且mapping需要执行命令增加location  geo_type
	/*
	  curl -XPUT 'http://localhost:9200/person/_mapping/user' -d '
	{
		"user" : {
		"properties" : {
			"location" : {"type" : "geo_point", "store" : true }
		}
	}
	}'
	*/
	private String location;

	@Field(type=FieldType.Double,store=true)
	private double score;

	@Field(type=FieldType.Ip,store=true)
	private String ip;

	@Field(type=FieldType.Nested,store=true)
	private List<Friend> friendList = new ArrayList<>();


	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List<Friend> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Friend> friendList) {
		this.friendList = friendList;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
