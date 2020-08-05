package com.eqchu.tinyp.controller;

import com.eqchu.tinyp.service.StudentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/student")
public class StudentInfoController {
    private static Logger logger = LoggerFactory.getLogger(StudentInfoController.class);
    @Autowired
    StudentInfoService service;

    @RequestMapping(value="/info",method = RequestMethod.GET)
    public Object getStudentInfoById(@RequestParam String id) throws Exception{
        logger.info("=====id:"+id+" =====");
        return service.getStudentInfoById(id);
    }

    @RequestMapping(value = "/score/list",method = RequestMethod.GET)
    public Object getScoreList(@RequestParam String id){

        return service.getScoreList();
    }

    @RequestMapping(value = "/rank/list",method = RequestMethod.GET)
    public Object getRankList(@RequestParam int type){
        logger.info("===== type:"+type+" =====");
        return service.getRankList();
    }

    @RequestMapping(value = "/test/list",method = RequestMethod.GET)
    public Object getTestList(){
        return service.getTestList();
    }

    @RequestMapping(value = "/test/by/id", method = RequestMethod.GET)
    public Object getTestById(@RequestParam int testId){
        return service.getTestById(testId);
    }

    @RequestMapping(value = "/getqqa/by/status",method = RequestMethod.GET)
    public Object getQQA(@RequestParam String answerStatus){
        return service.getQQA(answerStatus);
    }

    @RequestMapping(value = "/rewords",method = RequestMethod.GET)
    public Object getReword(){
        return service.getRewords();
    }
}

