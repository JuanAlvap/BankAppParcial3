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

    public boolean storageValidate(String initialBalance) {
        int initialBalanceDouble = Integer.parseInt(initialBalance);
        AccountCreateIdAccountValidate createIdAccountValidate = new AccountCreateIdAccountValidate();
        User selectedUser = null;
        String accountId = "";
        if (selectedUser != null) {
            accountId = createIdAccountValidate.createIdAccountValidate();
            AccountStorage storage = AccountStorage.getInstance();
            return storage.addAccount(new Account(accountId, selectedUser, initialBalanceDouble));
        }
        return false;
    }

}
