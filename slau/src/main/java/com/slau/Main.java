package com.slau;

import com.slau.math.SolutionThread;
import com.slau.math.algorithms.CramerAlgorithm;
import com.slau.math.algorithms.JordanGaussAlgorithm;
import com.slau.math.datasets.LinearSystem;

public class Main {
    public static void main(String[] args) {
        try {
            LinearSystem lSystem = new LinearSystem(3);
            SolutionThread cramerT = new SolutionThread(lSystem, new CramerAlgorithm());
            SolutionThread jordanGaussT = new SolutionThread(lSystem, new JordanGaussAlgorithm());
            SolutionThread[] threads = new SolutionThread[] {cramerT, jordanGaussT};
            try {
                for (SolutionThread thread : threads) {
                    thread.start();
                    thread.join();
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            lSystem.printAllSolutions();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
