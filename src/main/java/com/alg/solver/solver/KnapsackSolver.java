package com.alg.solver.solver;

import com.alg.solver.model.KnapsackData;
import com.alg.solver.model.Solution;
import com.alg.solver.strategy.KnapsackStrategy;

public class KnapsackSolver {
    private final KnapsackData data;
    private final KnapsackStrategy strategy;
    private Solution bestSolution;

    private KnapsackSolver(KnapsackData data, KnapsackStrategy strategy) {
        this.data = data;
        this.strategy = strategy;
    }

    public static Solution findSolution(KnapsackData data, KnapsackStrategy strategy) {
        return new KnapsackSolver(data, strategy).getSolution();
    }

    private Solution getSolution() {
        if (bestSolution == null) {
            bestSolution = strategy.solve(data);
        }
        return bestSolution;
    }

}
