/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

/**
 *
 * @author cande
 */
public class Deposit implements Movement {

    @Override
    public boolean execute(Account acc, double amount) {
        acc.setBalance(acc.getBalance() + amount);
        return true;

    }
}
