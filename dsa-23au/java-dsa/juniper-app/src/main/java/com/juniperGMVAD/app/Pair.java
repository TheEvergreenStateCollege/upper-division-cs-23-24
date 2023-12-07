package com.juniperGMVAD.app;

import java.util.Objects;

public class Pair<T, U> {
    public T first;
    public U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    // TODO: fix hacky workaround
    @Override
    public boolean equals(Object o) {
        Pair<T, U> temp = (Pair<T, U>) o;

        if (first.equals(temp.first) && second.equals(temp.second)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(first, second));
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
