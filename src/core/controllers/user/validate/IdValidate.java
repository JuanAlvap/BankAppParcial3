package core.controllers.user.validate;

import core.validate.NumberValidation;

public class IdValidate implements NumberValidation {

    public IdValidate() {
    }
    
    @Override
    public boolean validate(String id){
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
