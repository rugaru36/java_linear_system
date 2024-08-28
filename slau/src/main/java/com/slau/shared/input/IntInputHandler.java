/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slau.shared.input;

/**
 *
 * @author anton
 */
public class IntInputHandler extends InputHandler<Integer> implements IInputHandler<Integer> {

    public IntInputHandler() {
        this.stringInputParseFunc = (String val) -> Integer.valueOf(val);
        this.typeName = "integer number";
    }
}
