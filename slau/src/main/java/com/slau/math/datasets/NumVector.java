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
            this.elements = this.fInput.getList(size, null, null).toArray(new Float[size]);
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
        Float[] newVectorElements = new Float[size];
        for (int i = 0; i < size; i++) {
            newVectorElements[i] = this.elements[i];
        }
        return new NumVector(newVectorElements);
    }
    
    public void addElements(int srcIndex, int targetIndex, float coeff) {
        float targetValue = this.get(targetIndex);
        float srcValue = this.get(srcIndex);
        this.set(targetIndex, targetValue + (srcValue * coeff));
    }
}
