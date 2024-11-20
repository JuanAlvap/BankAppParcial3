package core.controllers.account.validate;

import core.controllers.user.UserController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountStorage;
import java.util.Random;

public class AccountStorageValidate {

    public AccountStorageValidate() {
    }

    public boolean storageValidate(String id, String initialBalance) {
        int idInt = Integer.parseInt(id);
        int initialBalanceDouble = Integer.parseInt(initialBalance);
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
                } else {
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
    }

}
