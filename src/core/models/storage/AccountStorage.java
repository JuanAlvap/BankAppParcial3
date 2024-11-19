/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author Juanpi
 */
public class AccountStorage {
    private static AccountStorage instance;
    
    private ArrayList<Account> accounts;
    
    private AccountStorage() {
        this.accounts = new ArrayList<>();
    }
    
    public static AccountStorage getInstance(){
        if(instance == null){
            instance = new AccountStorage();
        }
        return instance;
    }
    
    public boolean addAccount(Account account){
        for (Account a : this.accounts) {
            if(a.getId().equals(account.getId())){
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    
}
