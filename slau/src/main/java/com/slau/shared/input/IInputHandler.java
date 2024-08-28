package com.slau.shared.input;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface IInputHandler<Type> {

    Type getSingle(String preparedString, String message) throws Exception;

    ArrayList<Type> getList(int size, String preparedStr, String message) throws Exception;

    ArrayList<ArrayList<Type>> getListOfLists(int rows, int cols) throws Exception;

    IInputHandler<Type> setPredicate(Predicate<Type> predicate);
}
