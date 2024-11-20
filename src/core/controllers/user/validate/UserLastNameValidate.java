package core.controllers.user.validate;

public class UserLastNameValidate {

    public UserLastNameValidate() {
    }
    
    public boolean lastnameValidate(String lastname) {
        return !lastname.equals("");
    }
    
}
