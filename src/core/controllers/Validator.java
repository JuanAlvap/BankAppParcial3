/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.validate.NumberValidation;
import core.validate.StringValidation;

/**
 *
 * @author cande
 */
public class Validator {
    public boolean validate(StringValidation v, String text){
        return v.validate(text);
    }
    
    public boolean validate(NumberValidation v, String text){
        return v.validate(text);
    }
    
    
}
