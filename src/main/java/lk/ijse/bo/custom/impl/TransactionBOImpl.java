package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.TransactionDto;
import lk.ijse.entity.Transction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {
    TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);

    @Override
    public List<TransactionDto> getAllTransaction() throws Exception {
        List<TransactionDto> allTransation= new ArrayList<>();
        List<Transction> all = transactionDAO.getAll();
        for (Transction transction : all) {
            allTransation.add(new TransactionDto(transction.getId(),transction.getUser(),transction.getBranch(),transction.getBook(),transction.getDate(),transction.getContact()));
        }
        return allTransation;
    }
    @Override
    public boolean saveTransaction(TransactionDto dto) throws Exception {
        return transactionDAO.save(new Transction(dto.getId(), dto.getUser(), dto.getBranch(), dto.getBook(), dto.getDate(),dto.getContact()));
    }
    @Override
    public boolean updateTransaction(TransactionDto dto) throws Exception {
        return transactionDAO.update(new Transction(dto.getId(), dto.getUser(), dto.getBranch(), dto.getBook(), dto.getDate(),dto.getContact()));
    }

    @Override
    public boolean deleteTransaction(String id) throws SQLException, ClassNotFoundException, IOException {
        return transactionDAO.delete(id);
    }

    @Override
    public String generateNewTransactionID() throws SQLException, ClassNotFoundException, IOException {
        return transactionDAO.generateNewID();
    }


}
