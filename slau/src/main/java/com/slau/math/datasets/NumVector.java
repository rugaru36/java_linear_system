package com.slau.math.datasets;

import com.slau.shared.input.FloatInputHandler;
import com.slau.shared.input.IInputHandler;

public class NumVector implements Cloneable {
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
            this.size = size;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.elements = null;
        }
    }

    public int getSize() {
        return this.size;
    }

    public void set(int index, float val) {
        this.elements[index] = val;
    }

    public Float get(int index) {
        return this.elements[index];
    }

    public Float[] get() {
        return this.elements;
    }

    @Override
    public NumVector clone() throws CloneNotSupportedException {
        NumVector vector = (NumVector) super.clone();
        vector.elements = this.elements.clone();
        return vector;
    }
}
