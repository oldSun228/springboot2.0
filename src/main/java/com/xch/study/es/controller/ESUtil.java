package com.xch.study.es.controller;


import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
    private static final int TCP_PORT = 9300;
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
//        getClient().prepareIndex(index, type).setSource(jsonObject, XContentType.JSON);
        IndexResponse response = getClient().prepareIndex(index, type).setSource(jsonObject).get();
        System.out.println("map索引名称:" + response.getIndex() + "\n map类型:" + response.getType()
                + "\n map文档ID:" + response.getId() + "\n当前实例map状态:" + response.status());
    }

    /**
     * 添加索引:传入json字符串
     *
     * @param index
     * @param type
     * @param jsonObject
     * @return
     */
    public static void updataData(String index, String type, JSONObject jsonObject) {
//      Map<String, Object> map = new HashMap<String, Object>();
//    map.put("name", "大妹" );
//    map.put("age",20);
//    map.put("sex", "女");
//    map.put("address", "广东省广州市天河区上社");
//    map.put("phone", "15521202233");
//    map.put("height", "180");
//    map.put("weight", "70");
//    UpdateResponse updateResponse = client.prepareUpdate("species", "person", "AWNtYjiVjqSYg4HhYcQZ")
//            .setDoc(map).get();
//    System.out.println("updateResponse索引名称:" + updateResponse.getIndex() + "\n updateResponse类型:" + updateResponse.getType()
//            + "\n updateResponse文档ID:" + updateResponse.getId() + "\n当前实例updateResponse状态:" + updateResponse.status());
    }


    /**
     * 根据id查询
     */
    public Map<String, Object> queryById(String id) {
        GetResponse response = client.get(new GetRequest("logstash-2", "biglog", id)).actionGet();
        if (!response.isSourceEmpty()) {
            return response.getSource();
        }
        return null;
    }


    public static GetResponse queryData(String index, String type, String id) {
        GetResponse getResponse = client.prepareGet(index, type, id).get();
        System.out.println("索引库的数据:" + getResponse.getSourceAsString());
        return getResponse;
    }

    public static DeleteResponse deleteData(String index, String type, String id) {
        DeleteResponse deleteResponse = getClient().prepareDelete(index, type, id).get();
        return deleteResponse;
    }
}
