package core.controllers.user.validate;

import core.controllers.StringValidation;



public class UserAgeValidate implements StringValidation {

    public UserAgeValidate() {
    }

    @Override
    public boolean validate(String age) {
        try {
            int ageInt = Integer.parseInt(age);
            if (ageInt < 18) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
