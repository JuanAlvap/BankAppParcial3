package core.controllers.account.validate;

import core.controllers.StringValidation;

public class PositiveNumberValidate implements StringValidation {

    public PositiveNumberValidate() {
    }

    @Override
    public boolean validate(String initialBalance) {
        try {
            double initialBalanceDouble;
            initialBalanceDouble = Double.parseDouble(initialBalance);
            if (initialBalanceDouble < 0) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}
