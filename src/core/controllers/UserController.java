package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.UserStorage;
import java.util.ArrayList;

public class UserController {
    
    public UserController() {
    }
    
    public static Response registerUser(String idText, String firstnameText, String lastnameText, String ageText){
        try{
            int idInt, ageInt;
            try{
                idInt = Integer.parseInt(idText);
                if(idInt<1){
                    return new Response("Id must be numeric", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            if(firstnameText.equals("")){
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            if(lastnameText.equals("")){
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            try{
                ageInt = Integer.parseInt(ageText);
                if(ageInt<18){
                    return new Response("Age must be numeric", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Age must be numeric", Status.BAD_REQUEST);
            }
            
            UserStorage storage = UserStorage.getInstance();
            if(!storage.addUser(new User(idInt, firstnameText, lastnameText, ageInt))){
                return new Response("A user with that id already exists", Status.BAD_REQUEST);
            }
            
            return new Response("User register succesfully", Status.CREATED);
        }catch(Exception ex){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static ArrayList<User> getUsers(){
        return UserStorage.getInstance().getUsers();
    }
    
}
