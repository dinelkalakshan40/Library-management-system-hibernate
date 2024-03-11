package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDto;

import java.util.List;

public interface UserBO extends SuperBO {


    boolean saveUser(UserDto dto) throws Exception;

    public boolean updateUser(UserDto dto) throws Exception;

    UserDto getAllUsers(UserDto dto) throws Exception;

    List<UserDto> getAllUsers() throws Exception;
}
