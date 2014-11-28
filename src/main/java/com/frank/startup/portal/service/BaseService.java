package com.frank.startup.portal.service;

import java.util.List;

/**
 * @ClassName: BaseService.java
 * @Description: TODO
 * @author FrankWong
 * @version V1.0
 * @Date 2014-07-10 09:22:24
 */
public interface BaseService<T> {

	boolean add(T entity);

	boolean deleteById(int id);
	
	int deleteByIds(List<Integer> ids);

	boolean update(T entity);

	T getById(int id);
	
	@SuppressWarnings("hiding")
	<T> List<T> getList() ;
}
