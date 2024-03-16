package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Branch;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {
    BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCH);
    @Override
    public List<BranchDto> getAllBranches() throws Exception {
        List<BranchDto> allBranches= new ArrayList<>();
        List<Branch> all = branchDAO.getAll();
        for (Branch branch : all) {
            allBranches .add(new BranchDto(branch.getId(), branch.getBranch_name(), branch.getManager(), branch.getContact()));
        }
        return allBranches;
    }
    @Override
    public boolean saveBranch(BranchDto dto) throws Exception {
        return branchDAO.save(new Branch(dto.getId(), dto.getBranch_name(), dto.getManager(), dto.getContact()));
    }
    @Override
    public boolean updateBranch(BranchDto dto) throws Exception {
        return branchDAO.update(new Branch(dto.getId(), dto.getBranch_name(), dto.getManager(), dto.getContact()));
    }

    @Override
    public boolean deleteBranch(String id) throws SQLException, IOException, ClassNotFoundException {
        return branchDAO.delete(id);
    }

    @Override
    public String generateNewBranchID() throws SQLException, IOException, ClassNotFoundException {
        return branchDAO.generateNewID();
    }
}
