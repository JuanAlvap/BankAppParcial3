package core.controllers.user.validate;

public class UserFirstNameValidate {

    public UserFirstNameValidate() {
    }

    public boolean firstnameValidate(String firstname) {
        return !firstname.equals("");
    }

}
