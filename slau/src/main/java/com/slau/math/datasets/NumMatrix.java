package com.slau.math.datasets;

import java.util.Objects;

import com.slau.shared.input.FloatInputHandler;
import com.slau.shared.input.IInputHandler;

public class NumMatrix {
    private final IInputHandler<Float> fInput = new FloatInputHandler();

    private int rowsNum;
    private int colsNum;
    private Float[][] matrixElements;
    private Float determinant = null;

    public boolean equals(NumMatrix m) {
        if (m == null || this.colsNum != m.colsNum || this.rowsNum != m.rowsNum) {
            return false;
        }
        for (int i = 0; i < this.rowsNum; i++) {
            for (int j = 0; j < this.colsNum; j++) {
                if (!Objects.equals(this.matrixElements[i][j], m.matrixElements[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public Float getDeterminant() {
        if (this.determinant == null) {
            try {
                this.calculateDet();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
        return this.determinant;
    }

    public NumMatrix(int size) {
        try {
            this.rowsNum = size;
            this.colsNum = size;
            this.matrixElements = this.fInput.getTwoDArray(size, size);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public NumMatrix(int rows, int cols) {
        try {
            this.rowsNum = rows;
            this.colsNum = cols;
            this.matrixElements = this.fInput.getTwoDArray(rows, cols);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public NumMatrix(Float[][] matrixElements) {
        this.rowsNum = matrixElements.length;
        this.colsNum = matrixElements[0].length;
        this.matrixElements = matrixElements;
    }

    public NumMatrix getClonedMatrix() {
        Float[][] newMatrixElements = new Float[this.rowsNum][this.colsNum];
        for (int i = 0; i < this.rowsNum; i++) {
            for (int j = 0; j < this.colsNum; j++) {
                newMatrixElements[i] = this.matrixElements[i].clone();
            }
        }
        return new NumMatrix(newMatrixElements);
    }

    public void setValue(int row, int col, float val) {
        this.matrixElements[row][col] = val;
    }

    // private methods

    private void calculateDet() {
        if (this.rowsNum != this.colsNum) {
            this.determinant = null;
            return;
        } else if (this.rowsNum == 2) {
            this.determinant = this.matrixElements[0][0] * this.matrixElements[1][1]
                    - this.matrixElements[0][1] * this.matrixElements[1][0];
            return;
        }

        float detAccumulator = 0F;
        final int row = 0;

        for (int i = 0; i < this.rowsNum; i++) {
            NumMatrix minor = this.getElementMinor(row, i);
            int sign = (int) Math.pow(-1, i);
            double matrixElement = this.matrixElements[row][i];
            detAccumulator += sign * matrixElement * minor.getDeterminant();
        }
        this.determinant = detAccumulator;
    }

    private NumMatrix getElementMinor(int elRow, int elCol) {
        try {
            int size = this.colsNum;
            int minorSize = size - 1;

            Float[][] newMatrixElements = new Float[this.rowsNum - 1][];
            int minorCurrRowNum = 0;

            for (int rowCounter = 0; rowCounter < size; rowCounter++) {
                int minorCurrColNum = 0;
                Float[] minorRow = new Float[minorSize];
                if (rowCounter == elRow)
                    continue;
                for (int colCounter = 0; colCounter < size; colCounter++) {
                    if (colCounter == elCol) {
                        continue;
                    }
                    minorRow[minorCurrColNum] = this.matrixElements[rowCounter][colCounter];
                    minorCurrColNum++;
                }
                newMatrixElements[minorCurrRowNum] = minorRow;
                minorCurrRowNum++;
            }
            return new NumMatrix(newMatrixElements);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

    }
}
