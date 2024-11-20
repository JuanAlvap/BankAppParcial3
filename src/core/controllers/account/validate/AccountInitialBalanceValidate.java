package core.controllers.account.validate;

public class AccountInitialBalanceValidate {

    public AccountInitialBalanceValidate() {
    }

    public boolean initialBalcanceValidate(String initialBalance) {
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
