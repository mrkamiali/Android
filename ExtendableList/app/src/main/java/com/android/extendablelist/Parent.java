package com.android.extendablelist;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Kamran ALi on 3/3/2016.
 */
public class Parent {
    String name;
    ArrayList<Child> child;

    public Parent(String name, ArrayList<Child> child) {
        this.name = name;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Child> getChild() {
        return child;
    }

    public void setChild(ArrayList<Child> child) {
        this.child = child;
    }


}
