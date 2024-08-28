package com.slau.shared.input;

import java.util.Scanner;
import java.util.function.Predicate;

public class FloatInputHandler extends InputHandler<Float> implements IInputHandler<Float> {

    public FloatInputHandler() {
        this.stringInputParseFunc = (String val) -> Float.valueOf(val);
        this.typeName = "float number";
    }
}
