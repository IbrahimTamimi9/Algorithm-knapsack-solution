package com.alg.solver.model;

import java.util.ArrayList;
import java.util.List;

public class KnapsackData {

    private final List<Item> availableItem;
    private final int maximumWeight;

    public KnapsackData(int maxSize) {
        availableItem = new ArrayList<>();
        this.maximumWeight = maxSize;
    }

    public int getMaximumWeight() {
        return maximumWeight;
    }

    public int getSize() {
        return availableItem.size();
    }

    public Item getData(int index) {
        return availableItem.get(index);
    }

    public void addItem(List<Item> item) {
        availableItem.addAll(item);
    }

}
