package core.controllers.user.validate;

public class UserIdValidate {

    public UserIdValidate() {
    }
    
    public boolean idValidate(String id){
        try{
            int idInt = Integer.parseInt(id);
                if(idInt<0 || idInt>999999999){
                    return false;
                }
            }catch(NumberFormatException ex){
                return false;
            }
        return true;
    }
    
}
