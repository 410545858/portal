package com.frank.startup.portal;

import com.frank.startup.portal.search.elastic.repository.Friend;
import com.frank.startup.portal.search.elastic.repository.SearchUserEntity;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.search.geo.GeoDistanceRangeFilter;
import org.elasticsearch.search.aggregations.bucket.range.geodistance.GeoDistance;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by FrankWong on 12/1/14.
 */
public class SearchTest {

    private static ElasticsearchTemplate esTemplate;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("UserTest.setUpBeforeClass");
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"
                , "classpath:spring-context.xml", "classpath:spring-others.xml"});
        esTemplate = (ElasticsearchTemplate) context.getBean("elasticsearchTemplate");
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {


    }

    /**
     * @Description: 在任何一个测试执行之前必须执行的代码
     * @param: @throws java.lang.Exception
     * @return: void
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @Description: 在任何一个测试执行之后必须执行的代码
     * @param: @throws java.lang.Exception
     * @return: void
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testAddIndices() {
        esTemplate.createIndex(SearchUserEntity.class);
    }

    @Test
    public final void testAddMapping() {
        esTemplate.putMapping(SearchUserEntity.class);
    }

    @Test
    public final void testSearch() {
        QueryBuilder matchQuery = QueryBuilders.matchQuery("name", "黄华锋").operator(MatchQueryBuilder.Operator.AND);
//		QueryBuilder qb2 = boolQuery()
//                .must(QueryBuilders.termQuery("content", "test1"))
//                .must(termQuery("content", "test4"))
//                .mustNot(termQuery("content", "test2"))
//                .should(termQuery("content", "test3"));
//
        QueryBuilder termQuery = QueryBuilders.termQuery("address", "中国江苏省");
        QueryBuilder ageRangeQuery = QueryBuilders.rangeQuery("age").from(19).to(30);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery().must(matchQuery).must(ageRangeQuery).must(termQuery);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery).build();
        Page<SearchUserEntity> sampleEntities = esTemplate.queryForPage(searchQuery, SearchUserEntity.class);
        System.out.println("result count: " + sampleEntities.getContent().size());
        if (sampleEntities.getContent().size() != 0) {
            for (int i = 0; i < sampleEntities.getContent().size(); i++) {
                System.out.print(sampleEntities.getContent().get(i).getName() + "  ");
                System.out.println(sampleEntities.getContent().get(i).getAge());
            }
        }
    }

    /**
     * match and sort and pagination
     */
    @Test
    public final void testMatchQuery() {
        QueryBuilder matchQuery = QueryBuilders.matchQuery("name", "测试人员").operator(MatchQueryBuilder.Operator.AND);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery).withPageable(new PageRequest(0, 20)).
                withSort(SortBuilders.fieldSort("name").order(SortOrder.ASC)).build();
        Page<SearchUserEntity> sampleEntities = esTemplate.queryForPage(searchQuery, SearchUserEntity.class);
        long count = esTemplate.count(searchQuery, SearchUserEntity.class);
        SearchResultMapper searchResultMapper ;
        System.out.println("count:"+count+"   list count: " + sampleEntities.getContent().size());
        System.out.println("sampleEntities.getTotalElements()"+sampleEntities.getTotalElements());
        System.out.println(sampleEntities.getTotalPages());
        System.out.println(sampleEntities.getSize());
        System.out.println(sampleEntities.getNumber());
        System.out.println(sampleEntities.getContent().size());
        if (sampleEntities.getContent().size() != 0) {
            for (int i = 0; i < sampleEntities.getContent().size(); i++) {
                System.out.print(sampleEntities.getContent().get(i).getName() + "  ");
                System.out.println(sampleEntities.getContent().get(i).getAge());
            }
        }

    }

    /**
     *
     */
    @Test
    public final void testTermQuery() {
        QueryBuilder termQuery = QueryBuilders.termQuery("address", "中国");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery).build();
        Page<SearchUserEntity> sampleEntities = esTemplate.queryForPage(searchQuery, SearchUserEntity.class);
        System.out.println("result count: " + sampleEntities.getContent().size());
        if (sampleEntities.getContent().size() != 0) {
            for (int i = 0; i < sampleEntities.getContent().size(); i++) {
                System.out.print(sampleEntities.getContent().get(i).getName() + "  ");
                System.out.println(sampleEntities.getContent().get(i).getAge());
            }
        }
    }

    @Test
    public final void testRangeQuery() {
        //闭区间
        QueryBuilder rangeQuery = QueryBuilders.rangeQuery("age").from(25).to(30);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(rangeQuery).build();
        Page<SearchUserEntity> sampleEntities = esTemplate.queryForPage(searchQuery, SearchUserEntity.class);
        System.out.println("result count: " + sampleEntities.getContent().size());
        if (sampleEntities.getContent().size() != 0) {
            for (int i = 0; i < sampleEntities.getContent().size(); i++) {
                System.out.print(sampleEntities.getContent().get(i).getName() + "  ");
                System.out.println(sampleEntities.getContent().get(i).getAge());
            }
        }

        //开区间
        QueryBuilder rangeQuery1 = QueryBuilders.rangeQuery("age").gte(29);
        QueryBuilder rangeQuery2 = QueryBuilders.rangeQuery("age").lte(24);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery().should(rangeQuery2).should(rangeQuery1).minimumNumberShouldMatch(1);
        searchQuery = new NativeSearchQueryBuilder().withQuery(boolQuery).build();
        sampleEntities = esTemplate.queryForPage(searchQuery, SearchUserEntity.class);
        System.out.println("result count: " + sampleEntities.getContent().size());
        if (sampleEntities.getContent().size() != 0) {
            for (int i = 0; i < sampleEntities.getContent().size(); i++) {
                System.out.print(sampleEntities.getContent().get(i).getName() + "  ");
                System.out.println(sampleEntities.getContent().get(i).getAge());
            }
        }
    }

    @Test
    public final void testBooleanQuery() {
        QueryBuilder matchQuery = QueryBuilders.matchQuery("name", "黄华锋").operator(MatchQueryBuilder.Operator.AND);
        QueryBuilder ageRangeQuery = QueryBuilders.rangeQuery("age").from(19).to(30);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery().must(matchQuery).must(ageRangeQuery);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery).build();
        Page<SearchUserEntity> sampleEntities = esTemplate.queryForPage(searchQuery, SearchUserEntity.class);
        System.out.println("result count: " + sampleEntities.getContent().size());
        if (sampleEntities.getContent().size() != 0) {
            for (int i = 0; i < sampleEntities.getContent().size(); i++) {
                System.out.print(sampleEntities.getContent().get(i).getName() + "  ");
                System.out.println(sampleEntities.getContent().get(i).getAge());
            }
        }
    }

    @Test
    public final void testGeoQuery() {
        double longitude = 127.9200;
        double latitude = 32.10109;
        GeoDistanceFilterBuilder filter = FilterBuilders.geoDistanceFilter("location").point(latitude, longitude).distance(95, DistanceUnit.KILOMETERS);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(filter)
                .withSort(SortBuilders.geoDistanceSort("location").point(latitude, longitude).order(SortOrder.ASC)).build();
        List<SearchUserEntity> sites = esTemplate.queryForList(searchQuery, SearchUserEntity.class);
        for (int i = 0; i < sites.size(); i++) {
            System.out.println(sites.get(i).getAddress());
        }
        System.out.println("finished");
        GeoDistanceRangeFilterBuilder geoRangeFilter = FilterBuilders.geoDistanceRangeFilter("location")
                .point(latitude, longitude)
                .from("94km")
                .to("95km")
                .includeLower(true)
                .includeUpper(false)
                .optimizeBbox("memory")
                .geoDistance(org.elasticsearch.common.geo.GeoDistance.ARC);

        searchQuery = new NativeSearchQueryBuilder().withFilter(geoRangeFilter).build();
        sites = esTemplate.queryForList(searchQuery, SearchUserEntity.class);
        for (int i = 0; i < sites.size(); i++) {
            System.out.println(sites.get(i).getAddress());
        }

    }

    @Test
    public final void testChildQuery() {
        QueryBuilder matchQuery = QueryBuilders.matchQuery("friendList.name", "王菲").operator(MatchQueryBuilder.Operator.AND);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery).build();
        Page<SearchUserEntity> sampleEntities = esTemplate.queryForPage(searchQuery, SearchUserEntity.class);
        System.out.println("result count: " + sampleEntities.getContent().size());
        if (sampleEntities.getContent().size() != 0) {
            for (int i = 0; i < sampleEntities.getContent().size(); i++) {
                System.out.print(sampleEntities.getContent().get(i).getName() + "  ");
                System.out.println(sampleEntities.getContent().get(i).getAge());
            }
        }
    }

    @Test
    public final void testAddIndex() {
        SearchUserEntity entry = new SearchUserEntity();
        entry.setName("徐文君");
        entry.setAddress("中国江苏省苏州市工业园区中海国际社区");
        entry.setAge(30);
        entry.setBirthDay(new Date());
        entry.setGender(true);
        entry.setScore(1910.0129);
        entry.setIp("192.168.2.110");
        GeoPoint geoPoint = new GeoPoint(32.10109, 128.9200);
        entry.setLocation("32.10109,128.9200");
        Friend friend = new Friend();
        List<Friend> friendList = new ArrayList<>();
        friend.setName("王菲");
        friend.setAge(29);
        friend.setAddress("中国江苏省苏州市工业园区中海国际");
        friend.setHomePoint(new GeoPoint(32.0999, 120.1112));
        friendList.add(friend);
        Friend friend1 = new Friend();
        friend1.setName("李少");
        friend1.setAge(25);
        friend1.setAddress("中国江苏省苏州市工业园区崧泽家园二区");
        friend1.setHomePoint(new GeoPoint(32.1399, 120.1412));
        friendList.add(friend1);
        Friend friend2 = new Friend();
        friend2.setName("冯健");
        friend2.setAge(26);
        friend2.setAddress("中国江苏省苏州市工业园区崧泽家园二区89幢");
        friend2.setHomePoint(new GeoPoint(32.13909, 120.14112));
        friendList.add(friend2);
        entry.setFriendList(friendList);
        for (int i = 0; i < 100; i++) {
            IndexQuery indexQuery = new IndexQuery();
            entry.setName("测试人员"+String.valueOf(i));
            indexQuery.setObject(entry);
            esTemplate.index(indexQuery);
        }
    }

}
