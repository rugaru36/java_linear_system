package com.slau.shared.input;

import java.util.function.Predicate;

public interface IInputHandler<Type> {

    Type getSingle(String preparedString, String message) throws Exception;

    Type[] getArray(int size, String preparedStr, String message) throws Exception;

    Type[][] getTwoDArray(int rows, int cols) throws Exception;

    IInputHandler<Type> setPredicate(Predicate<Type> predicate);
}
