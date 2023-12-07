package com.juniperGMVAD.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.juniperGMVAD.app.BinaryHeap.BinaryHeap;
import com.juniperGMVAD.app.Enum.Country;

public class Ranking {
    BinaryHeap<Pair<Country, Double>> heap = new BinaryHeap<>(new SortBySecond());
    List<Pair<Country, Double>> suffix = new ArrayList<Pair<Country, Double>>(); // items removed from heap, e.g. top values

    public class SortBySecond implements Comparator<Pair<Country, Double>> {
        @Override
        public int compare(Pair<Country, Double> pair1, Pair<Country, Double> pair2) {
            Double result = pair1.second - pair2.second;
            if (result > 0) {
                return 1;
            } else if (result == 0) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public void addValue(Country country, Double value) {
        resetSuffix();
        heap.insert(new Pair<Country, Double>(country, value));
    }

    public void resetRankings() {
        BinaryHeap<Pair<Country, Double>> heap = new BinaryHeap<>(new SortBySecond());
        List<Pair<Country, Double>> suffix = new ArrayList<Pair<Country, Double>>(); // items removed from heap, e.g. top values
    }

    public Pair<Country, Double> getTop() {
        Pair<Country, Double> max = heap.deleteMax();
        suffix.add(max);
        return max;
    }

    public List<Pair<Country, Double>> getTop(int num) {
        if (num <= suffix.size()) {
            return suffix.subList(0, num);
        }

        int suffixSize = suffix.size();
        for (int i = suffixSize; i <= num; i++) {
            if (heap.size() > 0) {
                suffix.add(heap.deleteMax());
            } else {
                return suffix;
            }
        }

        return suffix.subList(0, num);
    }

    public void printHeapDebug() {
        heap.printHeapDebug();
    }

    private void resetSuffix() {
        for (Pair<Country, Double> value : suffix) {
            heap.insert(value);
        }

        List<Pair<Country, Double>> suffix = new ArrayList<Pair<Country, Double>>();
    }
}
