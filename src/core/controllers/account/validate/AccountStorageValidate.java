package core.controllers.account.validate;

import core.controllers.user.UserController;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountStorage;

public class AccountStorageValidate {

    public AccountStorageValidate() {
    }

    public boolean storageValidate(String id, String initialBalance) {
        int idInt = Integer.parseInt(id);
        double initialBalanceDouble = Double.parseDouble(initialBalance);
        AccountCreateIdAccountValidate createIdAccountValidate = new AccountCreateIdAccountValidate();
        User selectedUser = null;
        for (User user : UserController.getUsers()) {
                if (user.getId() == idInt && selectedUser == null) {
                    selectedUser = user;
                }
            }
        String accountId = "";
        if (selectedUser != null) {
            accountId = createIdAccountValidate.createIdAccountValidate();
            AccountStorage storage = AccountStorage.getInstance();
            return storage.addAccount(new Account(accountId, selectedUser, initialBalanceDouble));
        } else {
            return false;
        }
    }

}
