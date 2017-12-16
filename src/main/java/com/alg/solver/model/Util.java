package com.alg.solver.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class Util {
    private Util() {
    }

    public static List<Item> generateRandomData(int maxElement) {
        List<Item> items = new ArrayList<>();
        Random rand = new Random();
        int range = 3;
        int weight, value;
        for (int i = 0; i < maxElement; i++, range++) {
            weight = rand.nextInt(range) + 1;
            value = (rand.nextInt(range) + 1) * 3 + rand.nextInt(20) + 1;
            items.add(new Item(weight, value));
        }
        Collections.shuffle(items);
        return items;
    }

}
