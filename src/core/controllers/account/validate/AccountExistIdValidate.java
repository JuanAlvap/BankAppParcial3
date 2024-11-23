package core.controllers.account.validate;

import core.validate.StringValidation;
import core.controllers.user.UserController;
import core.models.User;

public class AccountExistIdValidate implements StringValidation {

    public AccountExistIdValidate() {
    }
    
    @Override
    public boolean validate(String id){
        int idInt = Integer.parseInt(id);
            for (User user : UserController.getUsers()) {
                if (user.getId() == idInt) {
                    return true;
                }
            }
        return false;
    }
    
}
