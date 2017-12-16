package com.alg.solver.model;

import java.util.Arrays;
import java.util.List;

public final class Solution {

    private final List<Item> pickedItem;
    private long gainedValue;
    private long gainedWeight;
    private long totalTime;

    public Solution(List<Item> pickedItem, long totalTime) {
        this.pickedItem = pickedItem;
        gainedValue = -1;
        gainedWeight = -1;
        this.totalTime = totalTime;
    }

    public List<Item> getPickedItem() {
        return pickedItem;
    }

    public long getGainedValue() {
        if (gainedValue == -1) {
            gainedValue = pickedItem.stream().mapToInt(Item::getProfit).sum();
        }
        return gainedValue;
    }

    public long getGainedWeight() {
        if (gainedWeight == -1) {
            gainedWeight = pickedItem.stream().mapToInt(Item::getWeight).sum();
        }
        return gainedWeight;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "pickedItem=" + Arrays.toString(pickedItem.toArray()) +
                ", gainedValue=" + gainedValue +
                ", gainedWeight=" + gainedWeight +
                ", totalTime=" + totalTime +
                '}';
    }
}
