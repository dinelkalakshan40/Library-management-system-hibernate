package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO =(UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDto dto) throws Exception {
        return userDAO.save(new User(dto.getUsername(),dto.getPassword()));
    }


    @Override
    public  boolean updateUser(UserDto dto) throws Exception {
        return userDAO.update(new User(dto.getUsername(),dto.getPassword()));

    }

    @Override
    public UserDto getAllUsers(UserDto dto) throws Exception {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() throws Exception {
        List<User> userList = userDAO.getAll();

        List<UserDto> userDTOList = new ArrayList<>();

        for (User user :userList ) {
            userDTOList.add(new UserDto(user.getUsername(),user.getPassword()));
        }
        return userDTOList;
    }



}
