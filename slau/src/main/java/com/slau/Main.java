package com.slau;

import com.slau.math.SolutionThread;
import com.slau.math.algorithms.CramerAlgorithm;
import com.slau.math.datasets.LinearSystem;

public class Main {
    public static void main(String[] args) {
        try {
            LinearSystem lSystem = new LinearSystem(3);
            SolutionThread stCramer = new SolutionThread(lSystem, new CramerAlgorithm());
            stCramer.start();
            try {
                stCramer.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            lSystem.printAllSolutions();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
