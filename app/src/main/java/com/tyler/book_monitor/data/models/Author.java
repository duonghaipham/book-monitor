package com.tyler.book_monitor.data.models;

public class Author implements IObject {
    private String name;
    private String avatar;

    public Author(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }
}
