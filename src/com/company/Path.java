package com.company;

import java.util.ArrayList;

public class Path {

    private ArrayList<Integer> path;
    private int weight;

    public Path(ArrayList<Integer> path, int weight) {
        this.path = path;
        this.weight = weight;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public int getWeight() {
        return weight;
    }
}
