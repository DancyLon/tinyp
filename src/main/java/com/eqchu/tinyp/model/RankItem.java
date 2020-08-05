package com.eqchu.tinyp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RankItem {
    private String id;
    private String name;
    private String address;
    private String touxian;
    private String res;
    private String logo;

    public RankItem(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTouxian() {
        return touxian;
    }

    public void setTouxian(String touxian) {
        this.touxian = touxian;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "RankItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", touxian='" + touxian + '\'' +
                ", res='" + res + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
