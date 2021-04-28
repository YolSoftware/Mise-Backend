package Yol.mise.Artifact.dao;

import Yol.mise.Artifact.dto.DBForecastViewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// db의 forecast view 테이블 JPA DAO
@Mapper
@Repository
public interface DBForecastViewDAO extends JpaRepository<DBForecastViewDTO, String> {
    @Override
    Optional<DBForecastViewDTO> findById(String s);

}
