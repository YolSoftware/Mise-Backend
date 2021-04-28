package Yol.mise.Artifact.dao;


//import Yol.mise.Artifact.dto.DBStinfoDTO;
import Yol.mise.Artifact.dto.DBStinfoDTO;
import Yol.mise.Artifact.dto.DBtmafDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JPA Repository
@Mapper
@Repository
public interface DBtmafDAO extends JpaRepository<DBtmafDTO, String> {

}