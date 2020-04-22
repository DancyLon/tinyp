package com.eqchu.project.service;

import com.eqchu.project.model.ShortInfo;
import com.eqchu.project.utils.CommonUtils;
import com.mongodb.BasicDBList;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.*;

import java.util.UUID;

@Service
public class InfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MongoTemplate mongo;

    public ShortInfo createShortInfo(ShortInfo info) throws Exception{
        logger.info("=====插入发布信息=====");
        logger.debug(info.toString());
        info.setInfoId(UUID.randomUUID().toString());
        String nowTimeString = CommonUtils.getNowTimeFormat("yyyy-MM-dd HH:mm:ss");
        info.setPublishDate(nowTimeString);
        info.setModifyDate(nowTimeString);
        mongo.save(info,"short_info");
        return info;
    }

    public List<Document> getInfoListByCategory(String category) throws Exception{
        logger.info("=====获取发布信息列表："+category+"=====");
        Query query = Query.query(Criteria.where("category").is(category));
        query.fields().exclude("_id");
        List<Document> list =  mongo.find(query,Document.class,"short_info");
        return list;
    }
}
