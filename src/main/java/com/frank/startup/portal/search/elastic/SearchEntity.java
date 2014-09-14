package com.frank.startup.portal.search.elastic;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ClassName:     SearchEntity.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 下午3:50:31 
 */
@Document( indexName = "person" , type = "user")
public class SearchEntity {

	@Id
	private String id;
	
	@Field( type = FieldType.String ,indexAnalyzer="ik")
	private String name;
	
	
}
