package core.controllers.user.validate;

import core.models.User;
import core.models.storage.UserStorage;

public class UserStorageValidate {

    public UserStorageValidate() {
    }

    public boolean storageValidate(String idText, String firstnameText, String lastnameText, String ageText) {
        int idInt = Integer.parseInt(idText);
        int ageInt = Integer.parseInt(ageText);
        UserStorage storage = UserStorage.getInstance();
        return storage.addUser(new User(idInt, firstnameText, lastnameText, ageInt));
    }

}
