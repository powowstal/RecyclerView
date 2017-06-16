package com.nbmaps.recyclerview.pojo;

import com.nbmaps.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 15.06.2017.
 */
public class Person {
    public String name;
    public String age;
    public int photoId;

    private List<Person> persons;

    public Person(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }

    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new Person("bird1", "blue", R.mipmap.bird1));
        persons.add(new Person("bird2", "red", R.mipmap.bird2));
        persons.add(new Person("bird3", "gray", R.mipmap.bird3));
        persons.add(new Person("bird4", "chapchaps", R.mipmap.bird4));
    }
}