package com.alg.solver.strategy;

import com.alg.solver.model.BinarySolution;
import com.alg.solver.model.Item;
import com.alg.solver.model.KnapsackData;
import com.alg.solver.model.Solution;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface KnapsackStrategy {

    Solution solve(KnapsackData data);

    default List<Item> generateSolution(KnapsackData knapsackData, BinarySolution solution) {
        List<Item> pickedItem = new ArrayList<>();
        for (int i = 0; i < knapsackData.getSize(); i++) {
            if (solution.getBit(i) == 1) {
                pickedItem.add(knapsackData.getData(i));
            }
        }
        return pickedItem;
    }
}
