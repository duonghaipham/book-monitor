package com.tyler.book_monitor.data.models;

public class Author implements IObject {
    private final String id;
    private final String name;
    private final String avatar;
    private final String introduction;

    public Author(String id, String name, String avatar, String introduction) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.introduction = introduction;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getIntroduction() {
        return introduction;
    }
}
