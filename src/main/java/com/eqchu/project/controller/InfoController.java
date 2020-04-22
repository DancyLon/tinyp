package com.eqchu.project.controller;

import com.eqchu.project.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.eqchu.project.model.ShortInfo;
import java.util.*;

@Component
@RestController
@RequestMapping("/filter/info")
public class InfoController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InfoService service;
    /**
     * 新建发布信息
     * @param info {@link ShortInfo}实体类
     * */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Object createShortInfo(@RequestBody ShortInfo info){
        try {
            ShortInfo i = service.createShortInfo(info);
            if (i != null && i.getModifyDate()!=null) {
                return "ok";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息列表异常：",e);
        }
        return null;
    }


    /**
     *  根据类型查询发布信息列表
     * @param category 类型
     * */
    @RequestMapping(value = "/list",method = RequestMethod.POST,
        produces = "application/json")
    public Object getInfoListByCategory(@RequestParam String category){
        try {
            return service.getInfoListByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST,
        produces = "application/json")
    public Object test(){
        return "test";
    }
}
