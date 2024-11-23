package core.controllers.user.validate;

import core.validate.NumberValidation;

public class UserAgeValidate implements NumberValidation {

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
