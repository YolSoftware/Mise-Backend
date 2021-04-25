package Yol.mise.Artifact.dao;


//import Yol.mise.Artifact.dto.DBStinfoDTO;
import Yol.mise.Artifact.dto.DBStinfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//JPA Repository
@Mapper
@Repository
public interface DBStinfoDAO extends JpaRepository<DBStinfoDTO, String> {
    //Generic type으로는 첫번째부터, <Entity, PrimaryKey 타입>을 넣는다.
//    List<DBStinfoDTO> selectStation(DBStinfoDTO param) throws Exception;
    //    List<UserDTO> selectUsers(UserDTO param) throws Exception;
    Optional<DBStinfoDTO> findByStationName(String station_name);
    Optional<DBStinfoDTO> deleteByStationName(String station_name);

}