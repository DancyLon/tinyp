package com.eqchu.project.controller;

import com.eqchu.project.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.eqchu.project.model.ShortInfo;

@Component
@RestController
@RequestMapping("/filter/info")
public class InfoController {

    @Autowired
    private InfoService service;
    /**
     * 新建发布信息
     * */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Object createShortInfo(@RequestBody ShortInfo info){
        return service.createShortInfo(info);
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Object test(){
        return "test";
    }
}
