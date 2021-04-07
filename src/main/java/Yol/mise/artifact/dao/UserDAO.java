package Yol.mise.artifact.dao;


import org.apache.ibatis.annotations.Mapper;
import Yol.mise.artifact.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDAO{
    List<UserDTO> selectUsers(UserDTO param) throws Exception;
    //    List<UserDTO> selectUsers(UserDTO param) throws Exception;
}