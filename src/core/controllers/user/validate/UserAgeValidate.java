package core.controllers.user.validate;

public class UserAgeValidate {

    public UserAgeValidate() {
    }

    public boolean ageValidate(String age) {
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
