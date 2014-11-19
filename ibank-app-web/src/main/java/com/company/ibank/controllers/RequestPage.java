package com.company.ibank.controllers;

public class RequestPage {
    private String page;
    private boolean redericted = false;

    public String getPage() {
        return page;
    }

    public boolean isRedericted() {
        return redericted;
    }

    public void setPage(String page) {
        this.page = (String)page;
    }

    public void setRedericted(boolean redericted) {
        this.redericted = redericted;
    }

    @Override
    public String toString() {
        return page;
    }
}
