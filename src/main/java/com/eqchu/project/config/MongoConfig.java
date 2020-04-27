package com.eqchu.project.config;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import java.util.*;

@Configuration
public class MongoConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    @Autowired
    public MongoDbFactory mongoDbFactory(MongoSettingProperties properties) {

        // 客户端配置（连接数，副本集群验证）
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(properties.getMaxConnectionsPerHost());
        builder.minConnectionsPerHost(properties.getMinConnectionsPerHost());
        if (properties.getReplicaSet() != null) {
            builder.requiredReplicaSetName(properties.getReplicaSet());
        }
        builder.threadsAllowedToBlockForConnectionMultiplier(
                properties.getThreadsAllowedToBlockForConnectionMultiplier());
        builder.serverSelectionTimeout(properties.getServerSelectionTimeout());
        builder.maxWaitTime(properties.getMaxWaitTime());
        builder.maxConnectionIdleTime(properties.getMaxConnectionIdleTime());
        builder.maxConnectionLifeTime(properties.getMaxConnectionLifeTime());
        builder.connectTimeout(properties.getConnectTimeout());
        builder.socketTimeout(properties.getSocketTimeout());
//         builder.socketKeepAlive(properties.getSocketKeepAlive());
        builder.sslEnabled(properties.getSslEnabled());
        builder.sslInvalidHostNameAllowed(properties.getSslInvalidHostNameAllowed());
//        builder.alwaysUseMBeans(properties.getAlwaysUseMBeans());
        builder.heartbeatFrequency(properties.getHeartbeatFrequency());
        builder.minHeartbeatFrequency(properties.getMinHeartbeatFrequency());
        builder.heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout());
        builder.heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout());
        builder.localThreshold(properties.getLocalThreshold());
        MongoClientOptions mongoClientOptions = builder.build();

        // MongoDB地址列表
        List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
//        for (String address : properties.getAddress()) {
//            String[] hostAndPort = address.split(":");
//            String host = hostAndPort[0];
//            Integer port = Integer.parseInt(hostAndPort[1]);
//            ServerAddress serverAddress = new ServerAddress(host, port);
//            serverAddresses.add(serverAddress);
//        }
        serverAddresses.add(
                new ServerAddress(properties.getHost(),properties.getPort()));
        logger.info("serverAddresses:" + serverAddresses.toString());

//         连接认证
         MongoCredential mongoCredential = null;
         if (properties.getUsername() != null) {
         	mongoCredential = MongoCredential.createScramSha1Credential(
         			properties.getUsername(), properties.getAuthenticationDatabase() != null
         					? properties.getAuthenticationDatabase() : properties.getDatabase(),
         			properties.getPassword().toCharArray());
         }

        // 创建认证客户端
         MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredential, mongoClientOptions);

        // 创建非认证客户端
//        MongoClient mongoClient = new MongoClient(serverAddresses, mongoClientOptions);

        // 创建MongoDbFactory
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, properties.getDatabase());
        return mongoDbFactory;
    }

    //没有这个配置，mongo在插入数据时会默认插入一个_class字段
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        try {
            mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        } catch (NoSuchBeanDefinitionException ignore) {
        }

        // Don't save _class to mongo
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return mappingConverter;
    }

}
