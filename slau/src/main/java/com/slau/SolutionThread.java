package com.slau;

import com.slau.math.LinearSystem;
import com.slau.math.algorithms.ISolutionAlgorithm;

public class SolutionThread extends Thread {
    private final LinearSystem lSystem;
    private final ISolutionAlgorithm algorithm;

    SolutionThread(LinearSystem lSystem, ISolutionAlgorithm algorithm) {
        this.lSystem = lSystem;
        this.algorithm = algorithm;
        this.algorithm.provideSystem(this.lSystem);
    }

    @Override
    public void run() {
        synchronized(this.lSystem) {
            this.lSystem.solve(this.algorithm);
        }
    }
}
