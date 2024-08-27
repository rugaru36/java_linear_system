package com.slau.shared.input;

import java.util.Scanner;
import java.util.function.Predicate;

public class FloatInputHandler implements IInputHandler<Float> {

    private Predicate<Float> predicate = null;
    private final Scanner inScanner = new Scanner(System.in);

    // ввод числа или парсинг готовой строки
    @Override
    public Float getSingle(String preparedStr, String message) {
        if (message == null) {
            message = "Input float number:";
        }
        String strToParse = preparedStr;
        while (true) {
            if (strToParse == null) {
                System.out.println(message);
                strToParse = this.inScanner.next();
            }
            try {
                Float val = Float.valueOf(strToParse);
                if (predicate != null && !predicate.test(val)) {
                    throw new NumberFormatException("number doesn`t satisfy provided predicate");
                }
                return val;
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                if (preparedStr != null) {
                    break;
                }
            }
        }
        return null;
    }

    @Override
    public Float[] getArray(int size, String preparedStr, String message) throws Exception {
        this.checkSize(size, null);
        String spaceRegex = "\\s+";
        String strToParse = preparedStr;
        Float[] resultArr = new Float[size];
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
                    Float value = this.getSingle(splittedStr[i], null);
                    if (value == null) {
                        throw new Exception("Invalid value on index " + i + "!");
                    }
                    resultArr[i] = value;
                }
                return resultArr;
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
    public Float[][] getTwoDArray(int rows, int cols) throws Exception {
        this.checkSize(rows, "rows");
        this.checkSize(cols, "cols");
        Float[][] resultFloatses = new Float[rows][];
        for (int currRow = 0; currRow < cols; currRow++) {
            String inputMsg = "Input line " + (currRow + 1) + "/" + rows + " separated by \" \"";
            resultFloatses[currRow] = this.getArray(cols, null, inputMsg);
        }
        return resultFloatses;
    }

    private void checkSize(int size, String sizeName) throws Exception {
        sizeName = sizeName == null ? "size" : sizeName;
        if (size <= 0) {
            throw new Exception(sizeName + " cant be less than zero!");
        }
    }

    @Override
    public FloatInputHandler setPredicate(Predicate<Float> predicate) {
        this.predicate = predicate;
        return this;
    }
}
