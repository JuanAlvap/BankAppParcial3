package core.controllers.user;

import core.controllers.Validator;
import core.controllers.user.validate.StringNotEmptyValidate;
import core.controllers.user.validate.UserAgeValidate;
import core.controllers.user.validate.IdValidate;
import core.controllers.user.validate.UserStorageValidate;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.UserStorage;
import java.util.ArrayList;

public class UserController{

    public UserController() {
    }

    public static Response registerUser(String idText, String firstnameText, String lastnameText, String ageText) {

        try {
            Validator validator = new Validator();
            IdValidate idValidate = new IdValidate();
            UserAgeValidate ageValidate = new UserAgeValidate();
            StringNotEmptyValidate notEmptyValidate = new StringNotEmptyValidate();
            UserStorageValidate storageValidate = new UserStorageValidate();

            if (!validator.validate(idValidate,idText)) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (!validator.validate(notEmptyValidate,firstnameText)) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(notEmptyValidate,lastnameText)) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(ageValidate,ageText)) {
                return new Response("Age must be numeric", Status.BAD_REQUEST);
            }

            if (!validator.validate(idText, firstnameText, lastnameText, ageText, storageValidate)) {
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
