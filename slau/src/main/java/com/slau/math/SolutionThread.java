package com.slau.math;

import com.slau.math.algorithms.ISolutionAlgorithm;
import com.slau.math.datasets.LinearSystem;

public class SolutionThread extends Thread {
    private final LinearSystem lSystem;
    private final ISolutionAlgorithm algorithm;

    public SolutionThread(LinearSystem lSystem, ISolutionAlgorithm algorithm) {
        this.lSystem = lSystem;
        this.algorithm = algorithm;
    }
    
    @Override
    public void run() {
        synchronized(this.lSystem) {
            this.lSystem.solve(this.algorithm);
        }
    }
}
