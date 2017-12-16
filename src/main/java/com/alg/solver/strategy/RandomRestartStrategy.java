package com.alg.solver.strategy;

import com.alg.solver.model.KnapsackData;
import com.alg.solver.model.Solution;

public class RandomRestartStrategy implements KnapsackStrategy {

    private final HillClimbingStrategy strategy;
    private final int retryLimit;

    public RandomRestartStrategy(HillClimbingStrategy strategy, int retryLimit) {
        this.strategy = strategy;
        this.retryLimit = retryLimit;
    }

    @Override
    public Solution solve(KnapsackData data) {
        long start = System.currentTimeMillis();
        Solution best = strategy.solve(data);
        for (int i = 0; i < retryLimit; i++) {
            Solution solution = strategy.solve(data);
            if (solution.getGainedValue() >= best.getGainedValue()) {
                best = solution;
            }
        }
        long end = System.currentTimeMillis();
        best.setTotalTime(end - start);
        return best;
    }
}