package Yol.mise.Artifact.dao;

import Yol.mise.Artifact.dto.DBrealtmDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface DBrealtmDAO extends JpaRepository<DBrealtmDTO, String> {
    Optional<DBrealtmDTO> findByStationName(String station_name);
}
