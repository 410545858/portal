/**
 * 
 */
package com.frank.startup.portal.search.elastic.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author frankwong
 *
 */
@Document( indexName = "person" , type = "user")
public class SearchUserEntity {
	@Id
	private String id;
	
	@Field( type = FieldType.String ,indexAnalyzer="ik",store=true)
	private String name;

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
