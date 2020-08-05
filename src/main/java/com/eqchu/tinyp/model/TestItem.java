package com.eqchu.tinyp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TestItem {
    private int testId;
    private String name;
    private String date;

    public TestItem(){ }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TestItem{" +
                "testId=" + testId +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
