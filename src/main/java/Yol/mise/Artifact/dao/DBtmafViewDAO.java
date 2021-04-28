package Yol.mise.Artifact.dao;

import Yol.mise.Artifact.dto.DBStinfoDTO;
import Yol.mise.Artifact.dto.DBtmafDTO;
import Yol.mise.Artifact.dto.DBtmafViewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface DBtmafViewDAO extends JpaRepository<DBtmafViewDTO, String> {

    Optional<DBtmafViewDTO> findByStationName(String station_name);
}