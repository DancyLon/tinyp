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
                return i;
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
    @RequestMapping(value = "/list",method = RequestMethod.GET,
        produces = "application/json")
    public Object getInfoListByCategory(@RequestParam String category){
        try {
            return service.getInfoListByCategory(category,0,
                    Arrays.asList("infoId","title","content","publishDate",
                            "region","imageURL"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照类别获取前[count]条数据，
     * 如果[count]为空则默认获取前6条
     * @param count 默认获取前[count]条数据
     * */
    @RequestMapping(value = "/list/latest",method = RequestMethod.GET)
    public Object getInfoListByCategoryLatest(@RequestParam String category,
        @RequestParam(required = false, defaultValue = "6") int count){
        try {
            return service.getInfoListByCategory(category,count,
                    Arrays.asList("infoId","title","publishDate","category"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过ID查找发布信息
     * @param infoId
     * */
    @RequestMapping(value = "/by",method = RequestMethod.GET)
    public Object getInfoById(@RequestParam String infoId){
        try {
            return service.getInfoById(infoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 快速搜索
     * 给出的条件可以是作者、标题、内容、区域
     * @param condition
     * */
    @RequestMapping(value = "by/condition",method = RequestMethod.GET)
    public Object getInfoByCondiiton(@RequestParam String condition){
        try {
            return service.getInfoByCondition(condition,0,
                    Arrays.asList("infoId","title","region","category",
                            "content","publishDate"));
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
