package net.devwiki.realmdemo.bean;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * 宠物狗
 * Created by DevWiki on 2016/8/30.
 */

public class Dog extends RealmObject {

    @Required
    private String id;
    private String name;
    private int color;

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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
