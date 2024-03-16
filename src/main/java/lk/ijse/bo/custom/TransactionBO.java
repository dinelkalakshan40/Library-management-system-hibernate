package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.TransactionDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TransactionBO extends SuperBO {
    List<TransactionDto> getAllTransaction() throws Exception;

    boolean saveTransaction(TransactionDto dto) throws Exception;

    boolean updateTransaction(TransactionDto dto) throws Exception;

    boolean deleteTransaction(String id) throws SQLException, ClassNotFoundException, IOException;
}
