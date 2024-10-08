package com.slau.math.algorithms;

import com.slau.math.datasets.LinearSystem;
import com.slau.math.datasets.NumMatrix;
import com.slau.math.datasets.NumVector;

public class CramerAlgorithm implements ISolutionAlgorithm {

    private LinearSystem system;

    @Override
    public void setSystem(LinearSystem system) {
        this.system = system;
    }

    @Override
    public Float[] getSolution() {
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
        return "Cramer";
    }

    private Float getOneSolutionByIndex(int index) {
        try {
            NumMatrix matrixWithReplacedCol
                    = this.getCoeffMatrixWithReplacedCol(index, this.system.addtionalVector);
            return matrixWithReplacedCol.getDeterminant() / this.system.coeffMatrix.getDeterminant();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    private NumMatrix getCoeffMatrixWithReplacedCol(int colNum, NumVector vectorToReplace)
            throws CloneNotSupportedException {
        NumMatrix clonedMatrix = this.system.coeffMatrix.clone();
        for (int i = 0; i < system.size; i++) {
            clonedMatrix.set(i, colNum, vectorToReplace.get(i));
        }
        return clonedMatrix;
    }
}
