package com.frank.startup.portal.service;

import com.frank.startup.portal.search.elastic.repository.SearchUserEntity;

public interface SearchUserService {

	void index(SearchUserEntity entry);
	
	void update(SearchUserEntity entry);
	
	void delete(SearchUserEntity entry);
	
	SearchUserEntity searchByName(String name);
	
}
