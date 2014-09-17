package com.frank.startup.portal.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

public interface BaseSearchService<T> {

	void index(T entry);
	
	void bulkIndex(List<T> list);
	
	void update(T entry);
	
	void delete(T entry);
	
	Page<T> search(SearchQuery query);
	
	
}
