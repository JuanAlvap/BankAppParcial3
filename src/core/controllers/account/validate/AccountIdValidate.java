package core.controllers.account.validate;

public class AccountIdValidate {

    public AccountIdValidate() {
    }

    public boolean idValidate(String id) {
        try {
            int idInt = Integer.parseInt(id);
            if (idInt < 0 || idInt > 999999999) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
