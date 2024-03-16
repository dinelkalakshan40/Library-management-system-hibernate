package lk.ijse.dao;

import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.BranchDAOImpl;
import lk.ijse.dao.custom.impl.TransactionDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return(daoFactory== null) ? daoFactory =new DAOFactory(): daoFactory;
    }

    public enum DAOTypes{
        REGISTER, USER,BOOK,BRANCH,TRANSACTION
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case TRANSACTION:
                return new TransactionDAOImpl();
            default:
                return null;
        }
    }

}
