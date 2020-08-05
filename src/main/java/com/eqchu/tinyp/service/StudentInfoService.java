package com.eqchu.tinyp.service;

import com.eqchu.tinyp.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentInfoService {

    public Object getStudentInfoById(String id) {
        Map<String,Object> map = new HashMap<String,Object>();

        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("name","梁晓茹");
        userInfo.put("nianji","2019级初二");
        userInfo.put("class","2班");
        userInfo.put("school","松江二中");
        userInfo.put("logo","");

        Map<String,Object> bangInfo = new HashMap<>();
        bangInfo.put("res",58);
        bangInfo.put("title","大将军");
        bangInfo.put("schoolRange",54);
        bangInfo.put("areaRange",100);
        bangInfo.put("regionRange",1122);

        map.put("userinfo",userInfo);
        map.put("dabanginfo",bangInfo);
        return map;
    }

    public Object getScoreList() {
        return null;
    }

    public Object getRankList() {
        List<RankItem> list = new ArrayList<RankItem>();
        for (int i = 0; i < 10; i++) {
            RankItem it = new RankItem();
            it.setAddress("松江二中");
            it.setId(""+i);
            it.setLogo("");
            it.setName("张"+i+"钱");
            it.setRes((100-i)+"");
            it.setTouxian("大将军");
            list.add(it);
        }
        return list;
    }

    public Object getTestList() {
        List<TestItem> list = new ArrayList<TestItem>();
        TestItem item1 = new TestItem();
        item1.setDate("2020-04-02");
        item1.setName("月考1");
        item1.setTestId(1);
        list.add(item1);

        TestItem item2 = new TestItem();
        item2.setDate("2020-05-02");
        item2.setName("月考2");
        item2.setTestId(2);
        list.add(item2);

        TestItem item3 = new TestItem();
        item3.setDate("2020-06-02");
        item3.setName("月考3");
        item3.setTestId(3);
        list.add(item3);
        return list;
    }

    public Object getTestById(int testId) {
        List<TestDetailItem> test = new ArrayList<>();
        TestDetailItem item1 = new TestDetailItem();
        item1.setTitle("语文");
        item1.setRes(99);
        item1.setPjres(89);
        item1.setTopres(100);
        item1.setRank(12);
        item1.setYouxiu(20);
        item1.setJige(60);
        item1.setBujige(20);
        item1.setChaoguo(30.5);
        item1.setTopbi(10.3);
        test.add(item1);

        TestDetailItem item2 = new TestDetailItem();
        item2.setTitle("数学");
        item2.setRes(79);
        item2.setPjres(78);
        item2.setTopres(100);
        item2.setRank(22);
        item2.setYouxiu(10);
        item2.setJige(60);
        item2.setBujige(30);
        item2.setChaoguo(10.5);
        item2.setTopbi(-30.3);
        test.add(item2);


        TestDetailItem item3 = new TestDetailItem();
        item3.setTitle("英语");
        item3.setRes(96);
        item3.setPjres(80);
        item3.setTopres(100);
        item3.setRank(8);
        item3.setYouxiu(23.2);
        item3.setJige(55.3);
        item3.setBujige(12.5);
        item3.setChaoguo(30.2);
        item3.setTopbi(-5.4);
        test.add(item3);

        TestDetailItem item4 = new TestDetailItem();
        item4.setTitle("合计");
        item4.setRes(274);
        item4.setPjres(240);
        item4.setTopres(288);
        item4.setRank(13);
        item4.setYouxiu(33.2);
        item4.setJige(45.8);
        item4.setBujige(21);
        item4.setChaoguo(77.2);
        item4.setTopbi(4.9);
        test.add(item4);

        return test;
    }

    public Object getQQA(String answerStatus) {
        Map<String,Object> map = new HashMap<>();
        map.put("title","");

        Map<String,Object> data = new HashMap<>();
        map.put("data",data);

        data.put("answerTime",90);
        data.put("answerStatus",answerStatus);

        List<QQA> qqaList = new ArrayList();
        QQA qqa1 = new QQA();
        qqa1.setName("窗前明月光，疑是地上霜，出自李白的哪首诗？");
        qqa1.setAnalysis("");
        qqa1.setAnswer("");
        qqa1.setRightAnswer("D");

        List<OptionItem> options1 = new ArrayList<>();
        options1.add(new OptionItem("次北固山下","A",false));
        options1.add(new OptionItem("南明离火","B",false));
        options1.add(new OptionItem("江南逢李龟年","C",false));
        options1.add(new OptionItem("静夜思","D",false));
        qqa1.setOptions(options1);
        qqaList.add(qqa1);

        QQA qqa2 = new QQA();
        qqa2.setName("窗前明月光，疑是地上霜，出自李白的哪首诗？");
        qqa2.setAnalysis("");
        qqa2.setAnswer("");
        qqa2.setRightAnswer("D");

        List<OptionItem> options2 = new ArrayList<>();
        options2.add(new OptionItem("次北固山下","A",false));
        options2.add(new OptionItem("南明离火","B",false));
        options2.add(new OptionItem("江南逢李龟年","C",false));
        options2.add(new OptionItem("静夜思","D",false));
        qqa2.setOptions(options2);
        qqaList.add(qqa2);

        map.put("list",qqaList);
        return map;
    }

    public Object getRewords() {
        List<RewordItem> list = new ArrayList();
        RewordItem item1 = new RewordItem();
        item1.setId(1);
        item1.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594466759835&di=9c313e0d6969b6b8c3abebed9e438de5&imgtype=0&src=http%3A%2F%2Fwww.zgjiadian.com%2FimageRepository%2F93996e1a-31c0-4e32-85a7-a40bef3189e0.jpg");
        item1.setMsg("县第一名");
        item1.setTitle("电饭锅");

        RewordItem item2 = new RewordItem();
        item2.setId(2);
        item2.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594466604990&di=c1cc030040aa0ad4ffdec85ca87bc6f8&imgtype=0&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1851477214%2C3854274545%26fm%3D214%26gp%3D0.jpg");
        item2.setMsg("市第一名");
        item2.setTitle("折叠自行车");

        RewordItem item3 = new RewordItem();
        item3.setId(3);
        item3.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594466821920&di=c75f8d1f85e71942c980e6d3494c1305&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ffront%2F408%2Fw1728h1080%2F20181229%2F_4Kw-hqwsysz1509145.jpg");
        item3.setMsg("区第一名");
        item3.setTitle("手机");

        RewordItem item4 = new RewordItem();
        item4.setId(4);
        item4.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594466850056&di=ffaae58a202cfb806a6319b5ebd32faa&imgtype=0&src=http%3A%2F%2Fimgs.bzw315.com%2FUploadFiles%2Fimage%2F2016%2F11%2F30%2F201611301507204651.jpg");
        item4.setMsg("省第一名");
        item4.setTitle("电视");

        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        return list;
    }
}
