package com.slau.math.algorithms;

import com.slau.math.datasets.LinearSystem;
import com.slau.math.datasets.NumMatrix;
import com.slau.math.datasets.NumVector;

public class JordanGaussAlgorithm implements ISolutionAlgorithm {

    private LinearSystem lSystem;
    private NumMatrix matrixToMakeUnit;
    private NumVector solutionsVector;

    @Override
    public void setSystem(LinearSystem system) {
        this.lSystem = system;
        try {
            this.matrixToMakeUnit = system.coeffMatrix.clone();
            this.solutionsVector = system.addtionalVector.clone();
        } catch (CloneNotSupportedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Float[] getSolution() {
        try {
            if (this.checkIsThereZeroLines()) {
                throw new Exception("zero lines before unit view");
            }
            this.bringToUnitMatrix();
            if (this.checkIsThereZeroLines()) {
                throw new Exception("zero lines after unit view");
            }
            return solutionsVector.get();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public String getName() {
        return "Jordan-Gauss";
    }

    private boolean checkIsThereZeroLines() {
        for (int i = 0; i < lSystem.size; i++) {
            boolean isZero = true;
            for (int j = 0; j < this.lSystem.size; j++) {
                if (this.lSystem.coeffMatrix.get(i, j) != 0) {
                    isZero = false;
                    break;
                }
            }
            if (isZero) {
                return true;
            }
        }
        return false;
    }

    private void bringToUnitMatrix() {
        for (int diagI = 0; diagI < this.lSystem.size; diagI++) {
            float baseDiagVal = matrixToMakeUnit.get(diagI, diagI);
            if (baseDiagVal != 0) {
                float oldVectorValOnDiagRow = this.solutionsVector.get(diagI);
                this.solutionsVector.set(diagI, oldVectorValOnDiagRow / baseDiagVal);

                for (int col = diagI; col < this.lSystem.size; col++) {
                    float oldVal = this.matrixToMakeUnit.get(diagI, col);
                    this.matrixToMakeUnit.set(diagI, col, oldVal / baseDiagVal);
                }
            }

            for (int rowBelowDiag = diagI + 1; rowBelowDiag < this.lSystem.size; rowBelowDiag++) {
                float rowCoeff = this.matrixToMakeUnit.get(rowBelowDiag, diagI);
                if (rowCoeff == 0) {
                    continue;
                }

                float rowBelowDiabOldVectorVal = this.solutionsVector.get(rowBelowDiag);
                float valToSubstractInVector = this.solutionsVector.get(diagI) * rowCoeff;
                float newVectorVal = rowBelowDiabOldVectorVal - valToSubstractInVector;

                this.solutionsVector.set(rowBelowDiag, newVectorVal);

                for (int col = diagI; col < this.lSystem.size; col++) {
                    float oldVal = this.matrixToMakeUnit.get(rowBelowDiag, col);
                    float valToSubstractInMatrix = this.matrixToMakeUnit.get(diagI, col) * rowCoeff;
                    this.matrixToMakeUnit.set(rowBelowDiag, col, oldVal - valToSubstractInMatrix);
                }
            }
        }
        /*
         * привели к ступенчатому виду, переходим к единичному (чистим всё, что над диагональю)
         */
//        for (int reverseDiagI = this.lSystem.size - 1; reverseDiagI > 0; reverseDiagI--) {
//            for (int rowOverDiag = reverseDiagI - 1; rowOverDiag >= 0; rowOverDiag--) {
//                float matrixOldValOverDiag = this.matrixToMakeUnit.get(rowOverDiag, reverseDiagI);
//                this.matrixToMakeUnit.set(rowOverDiag, reverseDiagI, 0);
//
//                float vectorDiagVal = this.solutionsVector.get(reverseDiagI);
//                float oldVectorOverDiagVal = this.solutionsVector.get(rowOverDiag);
//                float valToSubstractInVector = vectorDiagVal * matrixOldValOverDiag;
//                this.solutionsVector.set(rowOverDiag,
//                        oldVectorOverDiagVal - valToSubstractInVector);
//            }
//        }
    }
}
