package com.slau.math;

import java.util.ArrayList;

import com.slau.math.algorithms.ISolutionAlgorithm;
import com.slau.math.datasets.NumMatrix;
import com.slau.math.datasets.NumVector;

class SolutionSet {
    public String name;
    public Float[] solutions;

    public SolutionSet(String name, Float[] solutions) {
        this.name = name;
        this.solutions = solutions;
    }

    public void print() {
        if (this.solutions == null) {
            System.err.println("No solutions provided");
        }
        int solutionsCount = this.solutions.length;
        String namePresentation = "Algorithm name: " + this.name;
        String[] singleSolutionPresentations  = new String[this.solutions.length];
        for (int i = 0; i < solutionsCount; i++) {
            singleSolutionPresentations[i] = "X[" + (i + 1) + "] = " + this.solutions[i];
        }
        String allSolutionsPresentation = String.join(", ", singleSolutionPresentations);
        System.out.println(namePresentation + ": " + allSolutionsPresentation + "\n");
    }
}


public class LinearSystem {
    public NumMatrix coeffMatrix = null;
    public NumVector addtionalVector = null;
    public int size;
    private final ArrayList<SolutionSet> solutionSets = new ArrayList<>();

    public LinearSystem(int systemSize) {
        this.coeffMatrix = new NumMatrix(systemSize);
        this.addtionalVector = new NumVector(systemSize);
        this.size = systemSize;
    }

    public void solve(ISolutionAlgorithm algorithm) {
        String algorithmName = algorithm.getName();
        Float[] solutions = algorithm.provideSystem(this).solve();
        SolutionSet solutionSet = new SolutionSet(algorithmName, solutions);
        this.solutionSets.add(solutionSet);
    }

    public void printAllSolutions() {
        if (this.solutionSets.isEmpty()) {
            System.err.println("printAllSolutions: No solutions in list");
            return;
        }
        for (SolutionSet solutionSet : this.solutionSets) {
            solutionSet.print();
        }
    }
}
