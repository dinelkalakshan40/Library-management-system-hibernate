package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BranchDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BranchBO extends SuperBO {




   public List<BranchDto> getAllBranches() throws Exception;

    boolean saveBranch(BranchDto dto) throws Exception;

    boolean updateBranch(BranchDto dto) throws Exception;

    boolean deleteBranch(String id) throws SQLException, IOException, ClassNotFoundException;

    String generateNewBranchID() throws SQLException, IOException, ClassNotFoundException;
}
