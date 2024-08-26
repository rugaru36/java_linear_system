package com.slau.math.algorithms;

import com.slau.math.datasets.LinearSystem;

public interface ISolutionAlgorithm {
    void setSystem(LinearSystem system);
    Float[] getSolution();
    String getName();
}
