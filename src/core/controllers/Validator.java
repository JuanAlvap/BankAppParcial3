package core.controllers;

import core.validate.NumberValidation;
import core.validate.StringValidation;

public class Validator {

    public boolean validate(StringValidation v, String text) {
        return v.validate(text);
    }

    public boolean validate(NumberValidation v, String text) {
        return v.validate(text);
    }
}
