package com.slau.math.algorithms;

import com.slau.math.LinearSystem;
import com.slau.math.datasets.NumMatrix;
import com.slau.math.datasets.NumVector;

public class CramerAlgorithm implements ISolutionAlgorithm {

    private LinearSystem system;
    private final String name;

    public CramerAlgorithm() {
        this.name = "Cramer";
    }

    @Override
    public ISolutionAlgorithm provideSystem(LinearSystem system) {
        this.system = system;
        return this;
    }

    @Override
    public Float[] solve() {
        try {
            if (this.system == null) {
                throw new Exception("system is not provided");
            }
            Float det = this.system.coeffMatrix.getDeterminant();
            if (det == null || det == 0) {
                throw new Exception("system coeff matrix has no det");
            }

            Float[] solutions = new Float[system.size];
            for (int i = 0; i < this.system.size; i++) {
                solutions[i] = this.getOneSolutionByIndex(i);
            }
            return solutions;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    private Float getOneSolutionByIndex(int index) {
        NumMatrix matrixWithReplacedCol =
                this.getCoeffMatrixWithReplacedCol(index, this.system.addtionalVector);
        return matrixWithReplacedCol.getDeterminant() / this.system.coeffMatrix.getDeterminant();
    }

    private NumMatrix getCoeffMatrixWithReplacedCol(int colNum, NumVector vectorToReplace) {
        NumMatrix clonedMatrix = this.system.coeffMatrix.getClonedMatrix();
        for (int i = 0; i < system.size; i++) {
            clonedMatrix.setValue(i, colNum, vectorToReplace.getValue(i));
        }
        return clonedMatrix;
    }
}
