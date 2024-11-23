package core.controllers;

import core.models.Account;
import core.models.storage.AccountStorage;

public abstract class BaseService {

    protected Account findAccountById(String accountId) {
        for (Account account : AccountStorage.getInstance().getAccounts()) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    protected String validateAccountExistence(Account account, boolean isSource) {
        if (account == null) {
            return isSource ? "Source account not found" : "Destination account not found";
        }
        return null;
    }
}
