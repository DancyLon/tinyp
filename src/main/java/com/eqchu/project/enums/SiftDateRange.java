package com.eqchu.project.enums;

import java.util.Calendar;

/**
 * 信息筛选条件的时间类型，包括：
 * 今天、三天内、一周内、一个月内、半年内、一年内
 * */
public enum SiftDateRange {
    TODAY("today",1, Calendar.HOUR_OF_DAY,1),
    THREEDAY("three days",2,Calendar.DAY_OF_YEAR,3),
    ONEWEEK("one week",3,Calendar.WEEK_OF_YEAR,1),
    ONEMONTH("one month",4,Calendar.MONTH,1),
    HALFYEAR("half year",5,Calendar.MONTH,6),
    ONEYEAR("one year",6,Calendar.YEAR,1);


    SiftDateRange(String name, int value, int timeScale, int count) {
        this.name = name;
        this.value = value;
        this.timeScale = timeScale;
        this.count = count;
    }

    final private String name;
    final private int value;
    final private int timeScale;
    final private int count;

    public static SiftDateRange getOneByValue(int dataRange) {
        for(SiftDateRange s:SiftDateRange.values()){
            if (s.value == dataRange) {
                return s;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getTimeScale() {
        return timeScale;
    }

    public int getCount() {
        return count;
    }
}
