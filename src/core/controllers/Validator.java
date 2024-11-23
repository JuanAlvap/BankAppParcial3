package core.controllers;


import core.controllers.account.validate.AccountStorageValidate;
import core.controllers.user.validate.UserStorageValidate;

import core.validate.NumberValidation;
import core.validate.StringValidation;

public class Validator {

    public boolean validate(StringValidation v, String text) {
        return v.validate(text);
    }

    public boolean validate(NumberValidation v, String text) {
        return v.validate(text);
    }
    
     public boolean validate(String idText, String firstnameText, String lastnameText, String ageText, UserStorageValidate userStorageValidate) {
        return userStorageValidate.validate(idText, firstnameText, lastnameText, ageText);
    }
     
     public boolean validate(String id, String initialBalance, AccountStorageValidate accStorageValidate) {
         return accStorageValidate.validate(id, initialBalance);
    }
}
