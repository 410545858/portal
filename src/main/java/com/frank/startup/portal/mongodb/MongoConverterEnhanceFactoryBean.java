package com.frank.startup.portal.mongodb;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;

/**
 * @ClassName: MongoConverterEnhanceFactoryBean.java
 * @Description: 调用mongoTemplate的save方法时, spring-data-mongodb的TypeConverter会自动给document添加一个_class属性,通过该类解决此问题
 * @author FrankWong
 * @version V1.0
 * @Date 2013-12-4 下午8:30:52
 */
public class MongoConverterEnhanceFactoryBean implements
		FactoryBean<MappingMongoConverter> {

	private MappingMongoConverter converter;

	public void setConverter(MappingMongoConverter converter) {
		this.converter = converter;
	}

	public MappingMongoConverter getObject() throws Exception {
		MongoTypeMapper typeMapper = new DefaultMongoTypeMapper(null);
		converter.setTypeMapper(typeMapper);
		return converter;
	}

	public Class<?> getObjectType() {
		return MappingMongoConverter.class;
	}

	public boolean isSingleton() {
		return true;
	}

}
