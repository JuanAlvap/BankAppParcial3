package core.models.storage;

import java.util.ArrayList;
import core.models.User;

public class UserStorage {
    
    private static UserStorage instance;
    
    private ArrayList<User> users;
    
    private UserStorage() {
        this.users = new ArrayList<>();
    }
    
    public static UserStorage getInstance(){
        if(instance == null){
            instance = new UserStorage();
        }
        return instance;
    }
    
    public boolean addUser(User user){
        for (User u : this.users) {
            if(u.getId() == user.getId()){
                return false;
            }
        }
        this.users.add(user);
        return true;
    }
    
    public User getUser(int id){
        for (User user : this.users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    
    
}
