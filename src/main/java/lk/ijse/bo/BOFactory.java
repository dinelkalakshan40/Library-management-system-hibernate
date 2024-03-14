package lk.ijse.bo;

import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return(boFactory==null)? boFactory = new BOFactory() :boFactory;
    }

    public enum BOTypes{
        REGISTER, USER, BOOK
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case BOOK:
                return new BookBOImpl();
            default:
                return null;
        }
    }
}
