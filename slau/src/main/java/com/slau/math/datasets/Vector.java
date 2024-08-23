package com.slau.math.datasets;

import com.slau.shared.input.FloatInputHandler;
import com.slau.shared.input.IInputHandler;

public class Vector {
    /**
     * vector elements
     */
    private Float[] elements;
    private int size;
    private final IInputHandler<Float> fInput = new FloatInputHandler();

    public Vector(Float[] elements) {
        this.elements = elements;
        this.size = elements.length;
    }

    public Vector(int size) {
        try {
            this.elements = this.fInput.getArray(size, null, null);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.elements = null;
        }
    }

    public void setValue(int index, float val) {
        this.elements[index] = val;
    }

    public Float getValue(int index) {
        return this.elements[index];
    }
}
