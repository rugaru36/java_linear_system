package com.slau;

import com.slau.math.SolutionThread;
import com.slau.math.algorithms.CramerAlgorithm;
import com.slau.math.algorithms.JordanGaussAlgorithm;
import com.slau.math.datasets.LinearSystem;
import com.slau.shared.input.IntInputHandler;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        try {
            // getting system size
            Predicate<Integer> validSystemSizePredicate = x -> x > 1;
            String sizeMsg = "Input system size";
            int size = new IntInputHandler()
                    .setPredicate(validSystemSizePredicate)
                    .getSingle(null, sizeMsg);

            // creating solution threads
            LinearSystem lSystem = new LinearSystem(size);
            SolutionThread cramerT = new SolutionThread(lSystem, new CramerAlgorithm());
            SolutionThread jordanGaussT = new SolutionThread(lSystem, new JordanGaussAlgorithm());
            SolutionThread[] threads = new SolutionThread[]{cramerT, jordanGaussT};

            // working with threads
            try {
                for (SolutionThread thread : threads) {
                    thread.start();
                    thread.join();
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }

            // output
            lSystem.printAllSolutions();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
