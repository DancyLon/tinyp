package com.eqchu.project.model;

/**
 * 过滤信息列表请求体
 * */
public class SiftBody {
    private String condition;
    private String region;
    private String category;
    private int dataRange;

    public SiftBody(String conditon){
        this.condition = conditon;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDataRange() {
        return dataRange;
    }

    public void setDataRange(int dataRange) {
        this.dataRange = dataRange;
    }
}
