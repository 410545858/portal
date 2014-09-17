/**
 * 
 */
package com.frank.startup.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import com.frank.startup.portal.search.elastic.repository.SearchUserEntity;
import com.frank.startup.portal.service.SearchUserService;

/**
 * @author frankwong
 *
 */

@Service
public class SearchUserServiceImpl implements SearchUserService {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Override
	public void index(SearchUserEntity entry) {
		IndexQuery indexQuery = new IndexQuery();
		indexQuery.setId(entry.getId());
		indexQuery.setObject(entry);
		// creating mapping
		//elasticsearchTemplate.putMapping(SearchUserEntity.class);
		// indexing document
		elasticsearchTemplate.index(indexQuery);
		// refresh
		//elasticsearchTemplate.refresh(SearchUserEntity.class, true);
	}

	@Override
	public void update(SearchUserEntity entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SearchUserEntity entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SearchUserEntity searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
