package com.eqchu.project.service;

import com.eqchu.project.model.ShortInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MongoTemplate mongo;
    public Object createShortInfo(ShortInfo info) {
        logger.info("=====插入发布信息=====");
        logger.debug(info.toString());
        mongo.save(info,"short_info");
        return info.toString();
    }
}
