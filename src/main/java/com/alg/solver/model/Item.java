package com.alg.solver.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Objects;

public class Item implements Comparable<Item> {

    private IntegerProperty weight;
    private IntegerProperty profit;

    public Item(int weight, int profit) {
        this.weight = new SimpleIntegerProperty(weight);
        this.profit = new SimpleIntegerProperty(profit);
    }

    public int getWeight() {
        return weight.get();
    }

    public void setWeight(int weight) {
        this.weight = new SimpleIntegerProperty(weight);
    }

    public int getProfit() {
        return profit.get();
    }

    public void setProfit(int profit) {
        this.profit = new SimpleIntegerProperty(profit);
    }

    @Override
    public String toString() {
        return "Weight: " + weight + " Profit: " + profit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.weight);
        hash = 41 * hash + Objects.hashCode(this.profit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        return this.weight.equals(other.weight) && this.profit.equals(other.profit);
    }

    @Override
    public int compareTo(Item o) {
        return this.getWeight() - o.getWeight();
    }

}
