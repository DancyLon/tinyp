package com.eqchu.tinyp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "file:./config/mongo_pool.properties")
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoSettingProperties {

    private String host;
    private int port;
    private String replicaSet;
    private String database;
    private String username;
    private String password;
    private Integer minConnectionsPerHost = 0;
    private Integer maxConnectionsPerHost = 100;
    private Integer threadsAllowedToBlockForConnectionMultiplier = 5;
    private Integer serverSelectionTimeout = 30000;
    private Integer maxWaitTime = 120000;
    private Integer maxConnectionIdleTime = 0;
    private Integer maxConnectionLifeTime = 0;
    private Integer connectTimeout = 10000;
    private Integer socketTimeout = 0;
    private Boolean socketKeepAlive = false;
    private Boolean sslEnabled = false;
    private Boolean sslInvalidHostNameAllowed = false;
    private Boolean alwaysUseMBeans = false;
    private Integer heartbeatConnectTimeout = 20000;
    private Integer heartbeatSocketTimeout = 20000;
    private Integer minHeartbeatFrequency = 500;
    private Integer heartbeatFrequency = 10000;
    private Integer localThreshold = 15;
    private String authenticationDatabase;

    // 省略Getters和Setters方法


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getReplicaSet() {
        return replicaSet;
    }

    public void setReplicaSet(String replicaSet) {
        this.replicaSet = replicaSet;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMinConnectionsPerHost() {
        return minConnectionsPerHost;
    }

    public void setMinConnectionsPerHost(Integer minConnectionsPerHost) {
        this.minConnectionsPerHost = minConnectionsPerHost;
    }

    public Integer getMaxConnectionsPerHost() {
        return maxConnectionsPerHost;
    }

    public void setMaxConnectionsPerHost(Integer maxConnectionsPerHost) {
        this.maxConnectionsPerHost = maxConnectionsPerHost;
    }

    public Integer getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(Integer threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public Integer getServerSelectionTimeout() {
        return serverSelectionTimeout;
    }

    public void setServerSelectionTimeout(Integer serverSelectionTimeout) {
        this.serverSelectionTimeout = serverSelectionTimeout;
    }

    public Integer getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public Integer getMaxConnectionIdleTime() {
        return maxConnectionIdleTime;
    }

    public void setMaxConnectionIdleTime(Integer maxConnectionIdleTime) {
        this.maxConnectionIdleTime = maxConnectionIdleTime;
    }

    public Integer getMaxConnectionLifeTime() {
        return maxConnectionLifeTime;
    }

    public void setMaxConnectionLifeTime(Integer maxConnectionLifeTime) {
        this.maxConnectionLifeTime = maxConnectionLifeTime;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Boolean getSocketKeepAlive() {
        return socketKeepAlive;
    }

    public void setSocketKeepAlive(Boolean socketKeepAlive) {
        this.socketKeepAlive = socketKeepAlive;
    }

    public Boolean getSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(Boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public Boolean getSslInvalidHostNameAllowed() {
        return sslInvalidHostNameAllowed;
    }

    public void setSslInvalidHostNameAllowed(Boolean sslInvalidHostNameAllowed) {
        this.sslInvalidHostNameAllowed = sslInvalidHostNameAllowed;
    }

    public Boolean getAlwaysUseMBeans() {
        return alwaysUseMBeans;
    }

    public void setAlwaysUseMBeans(Boolean alwaysUseMBeans) {
        this.alwaysUseMBeans = alwaysUseMBeans;
    }

    public Integer getHeartbeatConnectTimeout() {
        return heartbeatConnectTimeout;
    }

    public void setHeartbeatConnectTimeout(Integer heartbeatConnectTimeout) {
        this.heartbeatConnectTimeout = heartbeatConnectTimeout;
    }

    public Integer getHeartbeatSocketTimeout() {
        return heartbeatSocketTimeout;
    }

    public void setHeartbeatSocketTimeout(Integer heartbeatSocketTimeout) {
        this.heartbeatSocketTimeout = heartbeatSocketTimeout;
    }

    public Integer getMinHeartbeatFrequency() {
        return minHeartbeatFrequency;
    }

    public void setMinHeartbeatFrequency(Integer minHeartbeatFrequency) {
        this.minHeartbeatFrequency = minHeartbeatFrequency;
    }

    public Integer getHeartbeatFrequency() {
        return heartbeatFrequency;
    }

    public void setHeartbeatFrequency(Integer heartbeatFrequency) {
        this.heartbeatFrequency = heartbeatFrequency;
    }

    public Integer getLocalThreshold() {
        return localThreshold;
    }

    public void setLocalThreshold(Integer localThreshold) {
        this.localThreshold = localThreshold;
    }

    public String getAuthenticationDatabase() {
        return authenticationDatabase;
    }

    public void setAuthenticationDatabase(String authenticationDatabase) {
        this.authenticationDatabase = authenticationDatabase;
    }
}