/**
 * 
 */
package com.frank.startup.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.frank.startup.portal.search.elastic.repository.SearchUserEntity;
import com.frank.startup.portal.service.BaseSearchService;

import javax.annotation.Resource;

/**
 * @author frankwong
 *
 */

@Service
public class SearchUserServiceImpl implements BaseSearchService<SearchUserEntity> {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Resource(name="elasticsearchClient")
	private Client client;

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
	public void bulkIndex(List<SearchUserEntity> list) {
		int size = list.size();
		List<IndexQuery> queries = new ArrayList<IndexQuery>();
		for(int i=0;i<size;i++){
			IndexQuery indexQuery = new IndexQuery();
			indexQuery.setId(list.get(i).getId());
			indexQuery.setObject(list.get(i));
			queries.add(indexQuery);
		}
		elasticsearchTemplate.bulkIndex(queries);
	}
	
	@Override
	public void update(SearchUserEntity entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SearchUserEntity entry) {
		elasticsearchTemplate.delete(SearchUserEntity.class, entry.getId());
	}

	@Override
	public Page<SearchUserEntity> search(SearchQuery query) {
//		QueryBuilder qb1 = MatchQuery("name", "新科电器");
		QueryBuilder matchQuery = QueryBuilders.matchQuery("name", "新科电器").operator(MatchQueryBuilder.Operator.AND);
//		QueryBuilder qb2 = boolQuery()   
//                .must(QueryBuilders.termQuery("content", "test1"))   
//                .must(termQuery("content", "test4"))   
//                .mustNot(termQuery("content", "test2"))  
//                .should(termQuery("content", "test3"));   
//
//		QueryBuilder qb3 = filteredQuery(   
//				termQuery("name.first", "shay"),   
//				rangeFilter("age")   
//	            .from(23)   
//	            .to(54)   
//	            .includeLower(true)   
//	            .includeUpper(false)   
//        ); 
		
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery).build();
		Page<SearchUserEntity> sampleEntities = elasticsearchTemplate.queryForPage(searchQuery,SearchUserEntity.class);
		System.out.println(sampleEntities.getContent().get(0).getName());
		return sampleEntities;
	}
}
