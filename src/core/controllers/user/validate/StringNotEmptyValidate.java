package core.controllers.user.validate;

import core.validate.StringValidation;



public class StringNotEmptyValidate implements StringValidation {

    @Override
    public boolean validate(String text) {
        return !text.equals("");
    }

}
