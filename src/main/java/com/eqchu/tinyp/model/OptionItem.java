package com.eqchu.tinyp.model;

public class OptionItem {
    private String optionName;
    private String order;
    private boolean status;

    public OptionItem() {
    }

    public OptionItem(String optionName, String order, boolean status) {
        this.optionName = optionName;
        this.order = order;
        this.status = status;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
