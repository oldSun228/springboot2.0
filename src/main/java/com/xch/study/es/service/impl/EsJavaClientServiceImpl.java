package com.xch.study.es.service.impl;

import com.xch.study.es.service.EsJavaClientService;
import javafx.util.Pair;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.slf4j.LoggerFactory;


import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;

/**
 * @author fgs
 * @data 2018/11/14 19:35
 */
public class EsJavaClientServiceImpl implements EsJavaClientService {

}
