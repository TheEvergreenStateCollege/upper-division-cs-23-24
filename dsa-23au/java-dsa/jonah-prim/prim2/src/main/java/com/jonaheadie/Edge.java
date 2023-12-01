package com.jonaheadie;

public class Edge {
    private String first;
    private String second;
    private int weight;

    public Edge (String first, String second, int weight) {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String name) {
        first = name;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String name) {
        second = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean sameNodes(Edge other) {
        if (first.equals(other.getFirst()) && second.equals(other.getSecond())) {
            return true;
        }

        if (second.equals(other.getFirst()) && first.equals(other.getSecond())) {
            return true;
        }

        return false;
    }

    public boolean isEqual(Edge other) {
        if (first.equals(other.getFirst()) && second.equals(other.getSecond()) && weight == other.getWeight()) {
            return true;
        }

        if (second.equals(other.getFirst()) && first.equals(other.getSecond()) && weight == other.getWeight()) {
            return true;
        }

        return false;
    }
}
