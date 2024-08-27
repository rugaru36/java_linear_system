/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slau.shared.input;

import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author anton
 */

public class IntInputHandler implements IInputHandler<Integer> {
    
    private Predicate<Integer> predicate;
    private final Scanner inScanner = new Scanner(System.in);
    
    @Override
    public Integer getSingle(String preparedString, String message) throws Exception {
        if (message == null) {
            message = "Input integer number:";
        }
        String strToParse = preparedString;
        while (true) {
            if (strToParse == null) {
                System.out.println(message);
                strToParse = this.inScanner.next();
            }
            try {
                Integer value = Integer.valueOf(strToParse);
                if (predicate != null && !predicate.test(value)) {
                    throw new NumberFormatException("number doesn`t satisfy provided predicate");
                }
                return value;
            } catch (NumberFormatException e) {
                strToParse = null;
                System.err.println(e.getMessage());
                if (preparedString != null) {
                    break;
                }
            }
        }
        return null;
    }

    @Override
    public Integer[] getArray(int size, String preparedStr, String message) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer[][] getTwoDArray(int rows, int cols) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IntInputHandler setPredicate(Predicate<Integer> predicate) {
        this.predicate = predicate;
        return this;
    }
}
