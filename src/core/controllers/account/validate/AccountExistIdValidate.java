package core.controllers.account.validate;

import core.controllers.StringValidation;
import core.controllers.user.UserController;
import core.models.User;

public class AccountExistIdValidate implements StringValidation {

    public AccountExistIdValidate() {
    }
    
    @Override
    public boolean validate(String id){
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
