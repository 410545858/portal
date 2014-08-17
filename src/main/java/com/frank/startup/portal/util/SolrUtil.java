package com.frank.startup.portal.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frank.startup.portal.search.solr.SearchEntity;
import com.frank.startup.portal.search.solr.SearchableEntity;

/**
 * @ClassName: SolrClient.java
 * @Description: TODO
 * @author FrankWong
 * @version V1.0
 * @Date 2014-7-10 下午4:16:27
 */
public class SolrUtil {

	Logger logger = LoggerFactory.getLogger(SolrUtil.class);

	public static String solrServerUrl = "";

	private static final int QUEUESIZE  = 100;
	private static final int THREADCOUNT  = 8;
	public static final int ROWS = 30;
	
	private static HttpSolrServer queryServer;
	private static ConcurrentUpdateSolrServer updateServer;
	
	public static HttpSolrServer getQueryServer(){
		return queryServer;
	}
	
	public static ConcurrentUpdateSolrServer getUpdateServer(){
		return updateServer;
	}
	
	public static void init(){
		updateServer = new ConcurrentUpdateSolrServer(solrServerUrl, QUEUESIZE, THREADCOUNT);
		queryServer = new HttpSolrServer(solrServerUrl);
		queryServer.setMaxRetries(1); // defaults to 0. > 1 not recommended.
		queryServer.setConnectionTimeout(5000); // 5 seconds to establish TCP
		// Setting the XML response parser is only required for cross
		// version compatibility and only when one side is 1.4.1 or
		// earlier and the other side is 3.1 or later.
		//server.setParser(new XMLResponseParser()); // binary parser is used by
													// default
		// The following settings are provided here for completeness.
		// They will not normally be required, and should only be used
		// after consulting javadocs to know whether they are truly required.
		queryServer.setSoTimeout(1000); // socket read timeout
		queryServer.setDefaultMaxConnectionsPerHost(1000);
		queryServer.setMaxTotalConnections(1000);
		queryServer.setFollowRedirects(false);
		// defaults to false
		// allowCompression defaults to false.
		// Server side must support gzip or deflate for this to have any effect.
		queryServer.setAllowCompression(true);
	}
	
	public static int create(SearchEntity entity){
		int result = -1;
		try{
			getUpdateServer().addBean(entity);
			getUpdateServer().commit();
			result = 0;
		}catch(Exception e){
			result = -1;
		}
		return result;
	}
	
	public static int update(SearchEntity entity){
		int result = -1;
		try{
			getUpdateServer().addBean(entity);
			getUpdateServer().commit();
			result = 0;
		}catch(Exception e){
			result = -1;
		}
		return result;
	}
	
	public static int update(String dbId,String category,String name){
		int result = -1;
		try{
			result = 0;
		}catch(Exception e){
			result = -1;
		}
		return result;
	}
	
	public static int delete(SearchEntity entity){
		int result = -1;
		try{
			getUpdateServer().deleteById(entity.getId());
			getUpdateServer().commit();
			result = 0;
		}catch(Exception e){
			result = -1;
		}
		return result;
	}
	
	public static int deleteByDBId(String dbId,String category){
		int result = -1;
		try{
			getUpdateServer().commit();
			result = 0;
		}catch(Exception e){
			result = -1;
		}
		return result;
	}
	
	
	public static int updateCount(SearchEntity entity){
		int result = -1;
		try{
			entity.setCount(entity.getCount()+1);
			update(entity);
			result = entity.getCount();
		}catch(Exception e){
			result = -1;
		}
		return result;
	}
	
	public static List<SearchEntity> searchByFiled(String[] fileds,String[] values) {
		SolrQuery query = new SolrQuery();
		if(null==fileds||null==values||fileds.length!=values.length){
			return null;
		}
		StringBuffer queryStr  = new StringBuffer();
		for(int i=0;i<fileds.length;i++){
			queryStr.append(fileds[i]).append(":").append(values[i]);
			if(i<fileds.length-1){
				queryStr.append(" AND ");
			}
		}
		query.setQuery(queryStr.toString());
		query.setStart(0);  
        query.setRows(SolrUtil.ROWS);
		query.addSort(SearchableEntity.COUNT_FIELD, SolrQuery.ORDER.asc );
		QueryResponse rsp;
		List<SearchEntity> list = new ArrayList<SearchEntity>();
		try {
			rsp = getQueryServer().query(query);
			SolrDocumentList docsList = rsp.getResults();
			SearchEntity entity;
			Iterator<SolrDocument> it = docsList.iterator();
			while(it.hasNext()){
				SolrDocument d = it.next();
				entity = new SearchEntity((String)d.getFieldValue("id"),(String)d.getFieldValue("name"),(String)d.getFieldValue("type"),(Integer)d.getFieldValue("db_id"),(Integer)d.getFieldValue("count"));
				list.add(entity);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void setSolrServerUrl(String url){
		solrServerUrl = url;
		init();
	}
	
	public static void main(String[] args) {
		
	}
}
