package Yol.mise.Artifact.dao;


import org.apache.ibatis.annotations.Mapper;
import Yol.mise.Artifact.dto.DBStNmDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DBStNmDAO {
    List<DBStNmDTO> selectUsers(DBStNmDTO param) throws Exception;
    //    List<UserDTO> selectUsers(UserDTO param) throws Exception;
}