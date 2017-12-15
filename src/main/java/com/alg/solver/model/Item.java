package com.alg.solver.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Objects;

public class Item implements Comparable<Item> {

    private final IntegerProperty weight;
    private final IntegerProperty value;

    public Item(int weight, int value) {
        this.weight = new SimpleIntegerProperty(weight);
        this.value = new SimpleIntegerProperty(value);
    }

    public int getWeight() {
        return weight.get();
    }

    public int getValue() {
        return value.get();
    }

    @Override
    public String toString() {
        return "Weight: " + weight + " Value: " + value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.weight);
        hash = 41 * hash + Objects.hashCode(this.value);
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
        return this.weight.equals(other.weight) && this.value.equals(other.value);
    }

    @Override
    public int compareTo(Item o) {
        return this.getWeight() - o.getWeight();
    }

}
