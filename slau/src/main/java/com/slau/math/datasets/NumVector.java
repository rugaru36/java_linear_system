package com.slau.math.datasets;

import com.slau.shared.input.FloatInputHandler;
import com.slau.shared.input.IInputHandler;

public class NumVector {
    /**
     * vector elements
     */
    private Float[] elements;
    private int size;

    private final IInputHandler<Float> fInput = new FloatInputHandler();


    public NumVector(Float[] elements) {
        this.elements = elements;
        this.size = elements.length;
    }

    public NumVector(int size) {
        try {
            this.elements = this.fInput.getArray(size, null, null);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.elements = null;
        }
    }

    public int getSize() {
        return size;
    }

    public void setValue(int index, float val) {
        this.elements[index] = val;
    }

    public Float getValue(int index) {
        return this.elements[index];
    }
}
