/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.account.validate;

import core.controllers.user.UserController;
import core.models.User;

/**
 *
 * @author Juanpi
 */
public class AccountExistIdValidate {

    public AccountExistIdValidate() {
    }
    
    public boolean existIdValidate(String id){
        int idInt = Integer.parseInt(id);
        boolean validate = false;
            for (User user : UserController.getUsers()) {
                if (user.getId() == idInt) {
                    validate = true;
                    break;
                }
            }
        return validate;
    }
    
}
