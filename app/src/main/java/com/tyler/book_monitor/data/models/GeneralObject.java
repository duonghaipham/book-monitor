package com.tyler.book_monitor.data.models;

public abstract class GeneralObject {
    protected String id;

    public GeneralObject(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
