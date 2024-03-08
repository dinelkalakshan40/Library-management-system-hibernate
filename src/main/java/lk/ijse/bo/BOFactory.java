package lk.ijse.bo;

import lk.ijse.bo.custom.impl.LoginBOImpl;
import lk.ijse.bo.custom.impl.RegisterBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return(boFactory==null)? boFactory = new BOFactory() :boFactory;
    }

    public enum BOTypes{
        REGISTER, LOGIN
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case LOGIN:
                return new LoginBOImpl();
            case REGISTER:
                return new RegisterBOImpl();
            default:
                return null;
        }
    }
}
