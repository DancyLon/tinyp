package com.eqchu.tinyp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TestDetailItem {
    private String title;
    private double res;
    private double pjres;
    private double topres;
    private int rank;
    private double youxiu;
    private double jige;
    private double bujige;
    private double chaoguo;
    private double topbi;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public double getPjres() {
        return pjres;
    }

    public void setPjres(double pjres) {
        this.pjres = pjres;
    }

    public double getTopres() {
        return topres;
    }

    public void setTopres(double topres) {
        this.topres = topres;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getYouxiu() {
        return youxiu;
    }

    public void setYouxiu(double youxiu) {
        this.youxiu = youxiu;
    }

    public double getJige() {
        return jige;
    }

    public void setJige(double jige) {
        this.jige = jige;
    }

    public double getBujige() {
        return bujige;
    }

    public void setBujige(double bujige) {
        this.bujige = bujige;
    }

    public double getChaoguo() {
        return chaoguo;
    }

    public void setChaoguo(double chaoguo) {
        this.chaoguo = chaoguo;
    }

    public double getTopbi() {
        return topbi;
    }

    public void setTopbi(double topbi) {
        this.topbi = topbi;
    }

    @Override
    public String toString() {
        return "TestDetailItem{" +
                "title='" + title + '\'' +
                ", res=" + res +
                ", pjres=" + pjres +
                ", topres=" + topres +
                ", rank=" + rank +
                ", youxiu=" + youxiu +
                ", jige=" + jige +
                ", bujige=" + bujige +
                ", chaoguo=" + chaoguo +
                ", topbi=" + topbi +
                '}';
    }
}
