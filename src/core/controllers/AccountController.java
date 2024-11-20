package core.controllers;

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
            int idInt;
            double initialBalanceDouble;
            try {
                idInt = Integer.parseInt(userIdText);
                if (idInt < 1) {
                    return new Response("Id must be numeric", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                initialBalanceDouble = Double.parseDouble(initialBalanceText);
                if (initialBalanceDouble < 0) {
                    return new Response("initial balance must be numeric", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("initial balance be numeric", Status.BAD_REQUEST);
            }

            boolean validate = false;
            for (User user : UserController.getUsers()) {
                if (user.getId() == idInt) {
                    validate = true;
                    break;
                }
            }
            if (!validate) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            User selectedUser = null;
            for (User user : UserController.getUsers()) {
                if (user.getId() == idInt && selectedUser == null) {
                    selectedUser = user;
                }
            }
            String accountId = "";
            if (selectedUser != null) {
                boolean validateRandom = true;
                //while (validateRandom) {
                    Random random = new Random();
                    int first = random.nextInt(1000);
                    int second = random.nextInt(1000000);
                    int third = random.nextInt(100);

                    accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);

                    for (Account account : AccountStorage.getInstance().getAccounts()) {
                        if (account.getId().equals(accountId)) {
                            validateRandom = true;
                            break;
                        }else{
                            validateRandom = false;
                        }
                    }
                //}
                AccountStorage storage = AccountStorage.getInstance();
                if (!storage.addAccount(new Account(accountId, selectedUser, initialBalanceDouble))) {
                    return new Response("A user with that id already exists", Status.BAD_REQUEST);
                }
                return new Response("User register succesfully", Status.CREATED);
            }
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    public static ArrayList<Account> getAccounts() {
        return AccountStorage.getInstance().getAccounts();
    }
}
