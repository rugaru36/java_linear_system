package com.slau.math.algorithms;

import com.slau.math.LinearSystem;

public interface ISolutionAlgorithm {
    ISolutionAlgorithm provideSystem(LinearSystem system);
    Float[] solve();
    String getName();
}
