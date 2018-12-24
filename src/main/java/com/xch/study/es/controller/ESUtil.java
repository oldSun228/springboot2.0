package com.xch.study.es.controller;


import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fgs
 * @data 2018/11/14 21:02
 */
public class ESUtil {
    //集群名,默认值elasticsearch
    private static final String CLUSTER_NAME = "my-application";
    //ES集群中某个节点
    private static final String TCP_IP = "192.168.1.6";
    //连接端口号
    private static final int TCP_PORT = 9301;
    //构建Settings对象
    //TransportClient对象，用于连接ES集群
    private static volatile TransportClient client;

    /**
     * 同步synchronized(*.class)代码块的作用和synchronized static方法作用一样,
     * 对当前对应的*.class进行持锁,static方法和.class一样都是锁的该类本身,同一个监听器
     *
     * @return
     * @throws UnknownHostException
     */
    public static TransportClient getClient() {
        if (client == null) {
            synchronized (TransportClient.class) {
                try {
                    Settings settings = Settings.builder().put("cluster.name", CLUSTER_NAME).build();
                    client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(TCP_IP), TCP_PORT));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        return client;
    }

    /**
     * 获取索引管理的IndicesAdminClient
     */
    public static IndicesAdminClient getAdminClient() {
        return getClient().admin().indices();
    }

    /**
     * 判定索引是否存在
     *
     * @param indexName
     * @return
     */
    public static boolean isExists(String indexName) {
        IndicesExistsResponse response = getAdminClient().prepareExists(indexName).get();
        return response.isExists() ? true : false;
    }


    /**
     * 创建索引
     *
     * @param indexName
     * @return
     */
    public static boolean createIndex(String indexName) {
        CreateIndexResponse createIndexResponse = getAdminClient().prepareCreate(indexName.toLowerCase()).get();
        return createIndexResponse.isAcknowledged() ? true : false;
    }

    /**
     * 创建索引
     *
     * @param indexName 索引名
     * @param shards    分片数
     * @param replicas  副本数
     * @return
     */
    public static boolean createIndex(String indexName, int shards, int replicas) {
        Settings settings = Settings.builder()
                .put("index.number_of_shards", shards)
                .put("index.number_of_replicas", replicas)
                .build();
        CreateIndexResponse createIndexResponse = getAdminClient()
                .prepareCreate(indexName.toLowerCase())
                .setSettings(settings)
                .execute().actionGet();
        return createIndexResponse.isAcknowledged() ? true : false;
    }

    /**
     * 位索引indexName设置mapping
     *
     * @param indexName
     * @param typeName
     * @param mapping
     */
    public static void setMapping(String indexName, String typeName, String mapping) {
        getAdminClient().preparePutMapping(indexName)
                .setType(typeName)
                .setSource(mapping, XContentType.JSON)
                .get();
    }

    /**
     * 删除索引
     *
     * @param indexName
     * @return
     */
    public static boolean deleteIndex(String indexName) {
        DeleteIndexResponse deleteResponse = null;
        if (!isExists(indexName)) {
            System.out.println(indexName + " not exists");
        } else {
            deleteResponse = getAdminClient()
                    .prepareDelete(indexName.toLowerCase())
                    .execute()
                    .actionGet();
        }
        return deleteResponse.isAcknowledged() ? true : false;
    }

    /**
     * 关闭客户端，释放资源
     */
    public static void closeClient() {
        client.close();
    }


    /**
     * 添加索引:传入json字符串
     *
     * @param index
     * @param type
     * @param jsonObject
     * @return
     */
    public static void saveData(String index, String type, JSONObject jsonObject) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.add(getClient().prepareIndex(index, type).setSource(jsonObject));
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            System.out.println("failures..............:" + bulkResponse.buildFailureMessage());
        }

//        IndexResponse response = getClient().prepareIndex(index, type).setSource(jsonObject).get();
//        System.out.println("map索引名称:" + response.getIndex() + "\n map类型:" + response.getType() + "\n map文档ID:" + response.getId() + "\n当前实例map状态:" + response.status());
    }

    /**
     * 添加索引:传入json字符串
     *
     * @param index
     * @param type
     * @param id
     * @return
     */
    public static UpdateResponse updataData(String index, String type, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        map.put("username", "username999");
        map.put("account", "account999");
        map.put("sex", "sex999");
        UpdateResponse updateResponse = getClient().prepareUpdate(index, type, id)
                .setDoc(map).get();
        return updateResponse;
    }


    public static GetResponse queryDataById(String index, String type, String id) {
        GetResponse getResponse = getClient().prepareGet(index, type, id).get();
        System.out.println("索引库的数据:" + getResponse.getSourceAsString());
        return getResponse;
    }

    public static DeleteResponse deleteData(String index, String type, String id) {
        DeleteResponse deleteResponse = getClient().prepareDelete(index, type, id).get();
        return deleteResponse;
    }

    public static SearchResponse queryData(String index, String type) {
        QueryBuilder qb = new MatchAllQueryBuilder();
        SearchResponse response = getClient().prepareSearch(index).setTypes(type).setQuery(qb).setFrom(0).setSize(10).get();
        return response;
    }

    public static SearchRequestBuilder queryDataForCondition(String index, String type) {
        //must 就像sql里的and
        QueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("account", "account7")).must(QueryBuilders.termQuery("username", "username7"));
        SearchRequestBuilder sv = getClient().prepareSearch(index).setTypes(type).setQuery(qb).setFrom(0).setSize(10);
        return sv;
    }

    public static SearchRequestBuilder queryDataForConditions(String index, String type) {
        QueryBuilder qb1 = QueryBuilders.termQuery("account", "account5");
        QueryBuilder qb2 = QueryBuilders.termQuery("account", "account6");

        SortBuilder sortBuilder = SortBuilders.fieldSort("createtime");
        sortBuilder.order(SortOrder.DESC);
        //should 就像sql里的or
        QueryBuilder s = QueryBuilders.boolQuery().should(qb1).should(qb2);
        SearchRequestBuilder sv = getClient().prepareSearch(index).setTypes(type).setQuery(s).addSort(sortBuilder).setFrom(0).setSize(10);
        return sv;
    }
}
