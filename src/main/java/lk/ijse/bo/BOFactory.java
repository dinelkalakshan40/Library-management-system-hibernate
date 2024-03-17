package lk.ijse.bo;

import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.bo.custom.impl.TransactionBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return(boFactory==null)? boFactory = new BOFactory() :boFactory;
    }

    public enum BOTypes{
        REGISTER, USER, BOOK,BRANCH,TRANSACTION
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case BOOK:
                return new BookBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case TRANSACTION:
                return new TransactionBOImpl();
            default:
                return null;
        }
    }
}
