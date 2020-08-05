package com.eqchu.tinyp.model;

import java.util.List;
import java.util.TreeMap;

public class QQA {
    private String name;
    private String rightAnswer;
    private String answer;
    private String analysis;
    private List<OptionItem> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public List<OptionItem> getOptions() {
        return options;
    }

    public void setOptions(List<OptionItem> options) {
        this.options = options;
    }
}
