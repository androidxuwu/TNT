package com.xw.tnt.bean;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import javax.inject.Inject;

public class User {

    private ObservableField<String> name;
    private ObservableInt age;

    @Inject
    public User(ObservableField<String> name, ObservableInt age) {
        this.name = name;
        this.age = age;
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableInt getAge() {
        return age;
    }

    public void setAge(ObservableInt age) {
        this.age = age;
    }
}
