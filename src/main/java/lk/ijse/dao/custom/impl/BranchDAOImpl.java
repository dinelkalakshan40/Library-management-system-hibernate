package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BranchDAOImpl implements BranchDAO {


    @Override
    public boolean save(Branch entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Branch entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DELETE FROM branch WHERE id = :id";
        NativeQuery<Branch> nativeQuery = session.createNativeQuery(sql);
        nativeQuery.setParameter("id",id);
        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public List<Branch> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM branch");
        nativeQuery.addEntity(Branch.class);
        List<Branch> branches = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return branches;
    }
    @Override
    public String   generateNewID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM branch ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if(id != null){
            String[] strings = id.split("Bch-");
            int newID = Integer.parseInt(strings[1]);
            newID++;
            String ID = String.valueOf(newID);
            int length = ID.length();
            if (length < 2){
                return "Bch-00"+newID;
            }else {
                if (length < 3){
                    return "Bch-0"+newID;
                }else {
                    return "Bch"+newID;
                }
            }
        }else {
            return "Bch-001";
        }
    }
}
