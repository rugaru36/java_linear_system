package com.slau.math.algorithms;

import com.slau.math.datasets.LinearSystem;

public interface ISolutionAlgorithm {
    ISolutionAlgorithm provideSystem(LinearSystem system);
    Float[] solve();
    String getName();
}
