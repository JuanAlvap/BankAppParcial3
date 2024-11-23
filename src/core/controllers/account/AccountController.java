package core.controllers.account;

import core.controllers.Validator;
import core.controllers.account.validate.AccountExistIdValidate;

import core.controllers.account.validate.PositiveNumberValidate;
import core.controllers.account.validate.AccountStorageValidate;
import core.controllers.user.validate.IdValidate;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.storage.AccountStorage;
import java.util.ArrayList;

public class AccountController {

    public AccountController() {
    }

    public static Response createAccount(String userIdText, String initialBalanceText) {
        try {
            Validator validator = new Validator();
            IdValidate idValidate = new IdValidate();
            PositiveNumberValidate initialBalanceValidate = new PositiveNumberValidate();
            AccountExistIdValidate existIdValidate = new AccountExistIdValidate();
            AccountStorageValidate storageValidate = new AccountStorageValidate();

            if (!validator.validate(idValidate,userIdText)) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (!validator.validate(initialBalanceValidate,initialBalanceText)) {
                return new Response("Initial balance must be numeric", Status.BAD_REQUEST);
            }

            if (!validator.validate(existIdValidate,userIdText)) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (!validator.validate(userIdText,initialBalanceText, storageValidate)) {
                return new Response("A user with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("User register succesfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);

        }
    }

    public static ArrayList<Account> getAccounts() {
        return AccountStorage.getInstance().getAccounts();
    }
}
