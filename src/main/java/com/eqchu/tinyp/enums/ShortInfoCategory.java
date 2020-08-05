package com.eqchu.tinyp.enums;

public enum ShortInfoCategory {
    BUILD_WEBSITE("搭建网站"),
    DESIGN("设计策划"),
    PRINTBRAND("喷绘招牌");

    final private String name;

    public String getName() {
        return name;
    }

    ShortInfoCategory(String name) {
        this.name = name;
    }
}


