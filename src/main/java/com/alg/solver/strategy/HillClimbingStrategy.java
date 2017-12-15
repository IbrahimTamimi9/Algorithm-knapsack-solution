package com.alg.solver.strategy;

import com.alg.solver.model.BinarySolution;
import com.alg.solver.model.Item;
import com.alg.solver.model.KnapsackData;
import com.alg.solver.model.Solution;

import java.util.List;

public class HillClimbingStrategy implements KnapsackStrategy {

    private final static double ALPHA = 1000000;
    private KnapsackData data;

    @Override
    public Solution solve(KnapsackData data) {
        this.data = data;

        long start = System.currentTimeMillis();
        BinarySolution solution = new BinarySolution(data.getSize());

        solution.shuffle();
        while (true) {
            BinarySolution newSolution = getNextState(solution);
            if (newSolution == solution) {
                break;
            }
            solution = newSolution;
        }

        long end = System.currentTimeMillis();
        List<Item> pickedItem = generateSolution(data, solution);
        return new Solution(pickedItem, end - start);
    }

    private BinarySolution getNextState(BinarySolution current) {
        BinarySolution newSolution = current;
        for (int i = 0; i < current.getSize(); i++) {
            BinarySolution mutated = new BinarySolution(current);
            mutated.flip(i);
            mutated.updateFitness(data, ALPHA);
            if (mutated.getFitness() < newSolution.getFitness()) {
                newSolution = mutated;
            }
        }
        return newSolution;
    }
}
