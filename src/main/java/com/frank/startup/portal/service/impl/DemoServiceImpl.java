package com.frank.startup.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.frank.startup.portal.service.BaseService;

public class DemoServiceImpl<T> implements BaseService<T> {


	@Autowired
	@Qualifier("portalMongoTemplate")
	private MongoTemplate portalMongoTemplate;
	

	@Autowired
	@Qualifier("consoleMongoTemplate")
	private MongoTemplate consoleMongoTemplate;
	
	
	@Override
	public void add(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
