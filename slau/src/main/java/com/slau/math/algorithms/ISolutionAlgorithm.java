package com.slau.math.algorithms;

import com.slau.math.datasets.LinearSystem;

public interface ISolutionAlgorithm {
    ISolutionAlgorithm setSystem(LinearSystem system);
    Float[] getSolution();
    String getName();
}
