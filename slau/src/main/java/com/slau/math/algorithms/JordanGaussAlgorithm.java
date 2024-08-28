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
                if (this.matrixToMakeUnit.get(i, j) != 0) {
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
            // делим строку на первый ненулевой элемент
            this.systemRowMultiply(diagI, 1 / baseDiagVal);

            for (int rowBelowDiag = diagI + 1; rowBelowDiag < this.lSystem.size; rowBelowDiag++) {
                float rowCoeff = this.matrixToMakeUnit.get(rowBelowDiag, diagI);
                this.addSystemRows(diagI, rowBelowDiag, -rowCoeff);
            }
        }
        /*
         * привели к ступенчатому виду, переходим к единичному (чистим всё, что над диагональю)
         */
        for (int reverseDiagI = this.lSystem.size - 1; reverseDiagI > 0; reverseDiagI--) {
            for (int rowOverDiag = reverseDiagI - 1; rowOverDiag >= 0; rowOverDiag--) {
                float rowCoeff = this.matrixToMakeUnit.get(rowOverDiag, reverseDiagI);
                this.addSystemRows(reverseDiagI, rowOverDiag, rowCoeff);
            }
        }
    }

    private void addSystemRows(int srcRow, int targetRow, float coeff) {
        if (coeff == 0 || coeff == 1) {
            return;
        }
        this.matrixToMakeUnit.addRows(srcRow, targetRow, coeff);
        this.solutionsVector.addElements(srcRow, targetRow, coeff);
    }

    private void systemRowMultiply(int row, float coeff) {
        float oldVectorValue = this.solutionsVector.get(row);
        this.solutionsVector.set(row, oldVectorValue * coeff);
        for (int col = 0; col < this.lSystem.size; col++) {
            float oldValue = this.matrixToMakeUnit.get(row, col);
            this.matrixToMakeUnit.set(row, col, oldValue * coeff);
        }
    }
}
