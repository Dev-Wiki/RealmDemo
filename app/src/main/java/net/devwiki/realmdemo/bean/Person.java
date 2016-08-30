package net.devwiki.realmdemo.bean;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * 人
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
    private RealmList<Cat> cats;
    private RealmList<Dog> dogs;

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

    public RealmList<Cat> getCats() {
        return cats;
    }

    public void setCats(RealmList<Cat> cats) {
        this.cats = cats;
    }

    public RealmList<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(RealmList<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", cats=" + getCatsInfo() +
                ", dogs=" + getDogsInfo() +
                '}';
    }

    private String getCatsInfo() {
        StringBuilder catInfo = new StringBuilder();
        for (Cat cat : cats) {
            catInfo.append(cat.toString());
            catInfo.append("\n");
        }
        return catInfo.toString();
    }

    private String getDogsInfo() {
        StringBuilder dogInfo = new StringBuilder();
        for (Dog dog : dogs) {
            dogInfo.append(dog.toString());
            dogInfo.append("\n");
        }
        return dogInfo.toString();
    }
}
