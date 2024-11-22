/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.controllers.transaction;

import core.controllers.utils.Response;

/**
 *
 * @author cande
 */
public interface TransactionType {
    public Response execute(String source, String destination, String amount);
}
