package com.slau.math.datasets;

import java.util.ArrayList;

import com.slau.math.algorithms.ISolutionAlgorithm;

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
        String nameStr = "Algorithm name: " + this.name;
        String[] singleSolutionStr = new String[this.solutions.length];
        for (int i = 0; i < solutionsCount; i++) {
            singleSolutionStr[i] = "X[" + (i + 1) + "] = " + this.solutions[i];
        }
        String allSolutionsPresentation = String.join(", ", singleSolutionStr);
        System.out.println(nameStr + ": " + allSolutionsPresentation);
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
        algorithm.setSystem(this);
        String algorithmName = algorithm.getName();
        Float[] solutions = algorithm.getSolution();
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
