package com.android.extencustomlist;

import java.util.List;

/**
 * Created by Kamran ALi on 3/4/2016.
 */
public class Parent {
    private String name;
    private List<Child> child;

    public Parent(String name, List<Child> child) {
        this.name = name;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChild() {
        return child;
    }

    public void setChild(List<Child> child) {
        this.child = child;
    }
}
