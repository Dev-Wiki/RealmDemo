package net.devwiki.realmdemo.bean;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Asia on 2016/8/29.
 */

public class Person extends RealmObject {

    public static final int SEX_MALE = 0;
    public static final int SEX_FEMALE = 1;

    @Required
    private String id;

    private String name;
    private int age;
    private int sex;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
