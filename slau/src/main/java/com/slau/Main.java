package com.slau;

import com.slau.math.datasets.Matrix;

public class Main {
    public static void main(String[] args) {
        try {
            Matrix matrix = new Matrix(4);
            float det = matrix.getDeterminant();
            System.out.println(det);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
