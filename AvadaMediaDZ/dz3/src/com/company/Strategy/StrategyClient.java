package com.company.Strategy;

import java.util.Arrays;

public class StrategyClient {

    Strategy strategy;

    public StrategyClient(Strategy strategy) {
        this.strategy = strategy;
    }

    public void sortArr(int[] Arr) {
        System.out.println("до -> " + Arrays.toString(Arr));
        strategy.sort(Arr);
        System.out.println("после -> " + Arrays.toString(Arr));
    }

}
