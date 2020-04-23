package com.eqchu.project.service;

import com.eqchu.project.model.ShortInfo;
import com.eqchu.project.utils.CommonUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.*;

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

    public List<Document> getInfoListByCategory(String category,int count,List<String> columnList) throws Exception{
        logger.info("=====获取发布信息列表："+category+"=====");
        Query query = Query.query(Criteria.where("category").is(category));
        //column
        query.fields().exclude("_id");
        if (columnList != null)
            columnList.forEach((column)->{
                query.fields().include(column);
            });

        //sort
        query.with(Sort.by(Sort.Order.desc("publishDate")));

        //limit
        if(count<=0)
            query.limit(100);
        else
            query.limit(count);

        List<Document> list =  mongo.find(query,Document.class,"short_info");
        return list;
    }

    public void getInfoByQueryContidion(String infoId) {

    }

    public ShortInfo getInfoById(String infoId) throws Exception {
       return mongo.findOne(Query.query(Criteria.where("infoId").is(infoId)),
                ShortInfo.class,"short_info");
    }

    public List<Document> getInfoByCondition(String condition,int count,List<String> columnList) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        //condition
        criteria.orOperator(
                Criteria.where("name").regex(condition),
                Criteria.where("title").regex(condition),
                Criteria.where("content").regex(condition),
                Criteria.where("region").regex(condition),
                 Criteria.where("category").regex(condition));
        query.addCriteria(criteria);

        //column
        query.fields().exclude("_id");
        if (columnList != null)
            columnList.forEach((column)->{
                query.fields().include(column);
            });

        //sort
        query.with(Sort.by(Sort.Order.desc("publishDate")));

        //limit
        if(count<=0)
            query.limit(100);
        else
            query.limit(count);

        List<Document> list = mongo.find(query,Document.class,"short_info");
        return list;
    }
}
