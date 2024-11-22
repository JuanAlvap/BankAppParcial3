package core.controllers.account.validate;

import core.controllers.StringValidation;

public class PositiveNumberValidate implements StringValidation {

    public PositiveNumberValidate() {
    }

    @Override
    public boolean validate(String num) {
        try {
            double numero;
            numero = Double.parseDouble(num);
            if (numero < 0) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}
