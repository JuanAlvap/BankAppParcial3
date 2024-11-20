package core.controllers.account;

import core.controllers.account.validate.AccountExistIdValidate;
import core.controllers.account.validate.AccountIdValidate;
import core.controllers.account.validate.AccountInitialBalanceValidate;
import core.controllers.account.validate.AccountStorageValidate;
import core.controllers.user.UserController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountStorage;
import java.util.ArrayList;
import java.util.Random;

public class AccountController {

    public AccountController() {
    }

    public static Response createAccount(String userIdText, String initialBalanceText) {
        try {
            AccountIdValidate idValidate = new AccountIdValidate();
            AccountInitialBalanceValidate initialBalanceValidate = new AccountInitialBalanceValidate();
            AccountExistIdValidate existIdValidate = new AccountExistIdValidate();
            AccountStorageValidate storageValidate = new AccountStorageValidate();

            if (!idValidate.idValidate(userIdText)) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (!initialBalanceValidate.initialBalcanceValidate(initialBalanceText)) {
                return new Response("initial balance must be numeric", Status.BAD_REQUEST);
            }

            if (!existIdValidate.existIdValidate(userIdText)) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (!storageValidate.storageValidate(initialBalanceText)) {
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
