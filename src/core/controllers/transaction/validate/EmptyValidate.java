/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transaction.validate;

/**
 *
 * @author Juanpi
 */
public class EmptyValidate {

    public EmptyValidate() {
    }
    
    public boolean isEmpty(String something){
        if(something.equals("")){
            return false;
        }
        return true;
    }
    
}
