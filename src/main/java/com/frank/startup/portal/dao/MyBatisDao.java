package com.frank.startup.portal.dao;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * @ClassName:     MyBatisDao.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:03:33 
 */
public class MyBatisDao extends SqlSessionDaoSupport {

	/** 
	 * @Title:        add 
	 * @Description:  新增数据
	 * @param:        key:Mapper
	 * @param:        object:操作对象
	 * @return:       int  0:新增失败 1:新增成功  
	 * @throws 
	 */
	public int add(String key, Object object) {
		return getSqlSession().insert(key, object);
	}
	
	/** 
	 * @Title:        deleteById 
	 * @Description:  通过主键删除数据
	 * @param:        key:mapper
	 * @param:        id:主键
	 * @return:       int 0:删除失败  1:删除成功   
	 * @throws 
	 */
	public int deleteById(String key, Serializable id) {
		return getSqlSession().delete(key, id);
	}

	public int deleteByIds(String key, List<Integer> ids) {
		return getSqlSession().delete(key, ids);
	}
	
	/** 
	 * @Title:        update 
	 * @Description:  更新操作
	 * @param:        key:mapper
	 * @param:        object:要更新的对象
	 * @return:       int 0:更新失败  1:更新成功
	 * @throws 
	 */
	public int update(String key,Object object){
		return getSqlSession().update(key, object);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Object params) {
		return (T) getSqlSession().selectOne(key, params);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getById(String key,int id){
		return (T) getSqlSession().selectOne(key, id);
	}
	
	public <T> List<T> getList(String key) {
		return getSqlSession().selectList(key);
	}
	
	public <T> List<T> getList(String key, Object params) {
		return getSqlSession().selectList(key, params);
	}
	
	public Long getCount(String key, Object params){
		return getSqlSession().selectOne(key, params);
	}
}
