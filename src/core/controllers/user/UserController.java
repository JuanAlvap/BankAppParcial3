package core.controllers.user;

import core.controllers.BaseController;
import core.controllers.user.validate.UserAgeValidate;
import core.controllers.user.validate.UserFirstNameValidate;
import core.controllers.user.validate.UserIdValidate;
import core.controllers.user.validate.UserLastNameValidate;
import core.controllers.user.validate.UserStorageValidate;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.UserStorage;
import java.util.ArrayList;

public class UserController extends BaseController {

    public UserController() {
    }

    public static Response registerUser(String idText, String firstnameText, String lastnameText, String ageText) {

        try {
            UserIdValidate idValidate = new UserIdValidate();
            UserAgeValidate ageValidate = new UserAgeValidate();
            UserFirstNameValidate firstnameValidate = new UserFirstNameValidate();
            UserLastNameValidate lastnameValidate = new UserLastNameValidate();
            UserStorageValidate storageValidate = new UserStorageValidate();

            if (!idValidate.idValidate(idText)) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (!firstnameValidate.firstnameValidate(firstnameText)) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (!lastnameValidate.lastnameValidate(lastnameText)) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }

            if (!ageValidate.ageValidate(ageText)) {
                return new Response("Age must be numeric", Status.BAD_REQUEST);
            }

            if (!storageValidate.storageValidate(idText, firstnameText, lastnameText, ageText)) {
                return new Response("A user with that id already exists", Status.BAD_REQUEST);
            }

            return new Response("User register succesfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }

    public static ArrayList<User> getUsers() {
        return UserStorage.getInstance().getUsers();
    }
}
