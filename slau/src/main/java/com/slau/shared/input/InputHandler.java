/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slau.shared.input;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * @author anton
 */
public abstract class InputHandler<InputType> implements IInputHandler<InputType> {

    private Predicate<InputType> predicate;
    private final Scanner inScanner = new Scanner(System.in);
    // from constructor
    protected Function<String, InputType> stringInputParseFunc;
    protected String typeName = "default";

    @Override
    public InputType getSingle(String preparedString, String message) throws Exception {
        if (message == null) {
            message = "Input " + this.typeName;
        }
        String strToParse = preparedString;
        while (true) {
            if (strToParse == null) {
                System.out.println(message);
                strToParse = this.inScanner.next();
            }
            try {
                InputType val = stringInputParseFunc.apply(strToParse);
                if (predicate != null && !predicate.test(val)) {
                    throw new Exception("input doesn`t satisfy provided predicate");
                }
                return val;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                if (preparedString != null) {
                    break;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<InputType> getList(int size, String preparedStr, String message) throws Exception {
        this.checkSize(size, null);
        String spaceRegex = "\\s+";
        String strToParse = preparedStr;
        ArrayList<InputType> result = new ArrayList<>();
        if (message == null) {
            message = "Input float array separated by \" \"";
        }
        while (true) {
            try {
                if (preparedStr == null) {
                    System.out.println(message);
                    strToParse = this.inScanner.nextLine();
                }
                String[] splittedStr = strToParse.split(spaceRegex);
                if (splittedStr.length != size) {
                    throw new Exception("Wrong array size");
                }
                for (int i = 0; i < size; i++) {
                    InputType value = this.getSingle(splittedStr[i], null);
                    if (value == null) {
                        throw new Exception("Invalid value on index " + i + "!");
                    }
                    result.add(value);
                }
                return result;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                if (preparedStr != null) {
                    break;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<ArrayList<InputType>> getListOfLists(int rows, int cols) throws Exception {
        this.checkSize(rows, "rows");
        this.checkSize(cols, "cols");
        ArrayList<ArrayList<InputType>> result = new ArrayList<>();
        for (int currRow = 0; currRow < cols; currRow++) {
            String inputMsg = "Input line " + (currRow + 1) + "/" + rows + " separated by \" \"";
            result.add(this.getList(cols, null, inputMsg));
        }
        return result;
    }

    @Override
    public IInputHandler<InputType> setPredicate(Predicate<InputType> predicate) {
        this.predicate = predicate;
        return this;
    }

    private void checkSize(int size, String sizeName) throws Exception {
        sizeName = sizeName == null ? "size" : sizeName;
        if (size <= 0) {
            throw new Exception(sizeName + " cant be less than zero!");
        }
    }
}
