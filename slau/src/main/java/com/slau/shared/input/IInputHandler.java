package com.slau.shared.input;

public interface IInputHandler<Type> {
    Type getSingle(String preparedString, String message) throws Exception;

    Type[] getArray(int size, String preparedStr, String message) throws Exception;

    Type[][] getTwoDArray(int rows, int cols) throws Exception;
}
