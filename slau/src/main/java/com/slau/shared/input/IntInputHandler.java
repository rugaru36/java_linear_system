/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slau.shared.input;

import java.util.Scanner;

/**
 *
 * @author anton
 */
public class IntInputHandler implements IInputHandler<Integer> {

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
                return Integer.valueOf(strToParse);
            } catch (NumberFormatException e) {
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
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer[][] getTwoDArray(int rows, int cols) throws Exception {
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
