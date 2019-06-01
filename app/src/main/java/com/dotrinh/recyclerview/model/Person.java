/*
 * Copyright (c) 2019.
 * Do Trinh - https://dotrinh.com
 * All Rights Reserved
 * Written by dotrinh contact to contact@dotrinh.com please.
 */

package com.dotrinh.recyclerview.model;

public class Person {
    int id;
    String name;
    boolean isCheck;

    public Person(int id, String name, boolean isCheck) {
        this.id = id;
        this.name = name;
        this.isCheck = isCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
