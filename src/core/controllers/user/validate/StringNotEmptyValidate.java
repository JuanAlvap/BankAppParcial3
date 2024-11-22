package core.controllers.user.validate;

import core.controllers.StringValidation;



public class StringNotEmptyValidate implements StringValidation {

    @Override
    public boolean validate(String text) {
        return !text.equals("");
    }

}
