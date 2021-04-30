package Yol.mise.Service;

import Yol.mise.Artifact.dao.DBrealtmDAO;
import Yol.mise.Artifact.dto.DBStinfoDTO;
import Yol.mise.Artifact.dto.DBrealtmDTO;
import Yol.mise.Artifact.dto.OPStnMsrDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DBrealtmService {
    @Autowired
    private DBrealtmDAO dBrealtmDAO;//repository

    //측정소 대기정보 상태 조회
    public Optional<DBrealtmDTO> findStationAir(String stname){
        final Optional<DBrealtmDTO> dBrealtmDTOOptional = dBrealtmDAO.findByStationName(stname);
        return dBrealtmDTOOptional;//Optional<DBrealtmDTO> dBrealtmDTOOptional
    }

    //모든 측정소 대기정보 상태 조회
    public List<DBrealtmDTO> findAllStationAir() throws Exception{
        final List<DBrealtmDTO> dBrealtmDTOOptional = dBrealtmDAO.findAll();
        return dBrealtmDTOOptional;
    }

    //측정소 대기정보 상태 업데이트
    @Transactional
    public boolean updateStationAir(String stname, OPStnMsrDTO opStnMsrDTO) throws Exception{
        Optional<DBrealtmDTO> dBrealtmDTOOptional = dBrealtmDAO.findByStationName(stname);
        //station_info.ifPresent(
        if(dBrealtmDTOOptional.isPresent()) {
            DBrealtmDTO new_station =
                    DBrealtmDTO.builder()
                            .stationNumber(dBrealtmDTOOptional.get().stationNumber) //중복되는 값으로 인식하면 insert 두번으로 인식하여 오류남
                            .stationName(stname)
                            .allAirGrade(opStnMsrDTO.getKhaiGrade())
                            .so2(opStnMsrDTO.getSo2Value())
                            .co(opStnMsrDTO.getCoValue())
                            .o3(opStnMsrDTO.getO3Value())
                            .no2(opStnMsrDTO.getNo2Value())
                            .pm10(opStnMsrDTO.getPm10Value())
                            .pm25(opStnMsrDTO.getPm25Value())
                            .coGrade(opStnMsrDTO.getCoGrade())
                            .o3Grade(opStnMsrDTO.getO3Grade())
                            .no2Grade(opStnMsrDTO.getNo2Grade())
                            .pm10Grade(opStnMsrDTO.getPm10Grade())
                            .pm25Grade(opStnMsrDTO.getPm25Grade())
                            .so2Flag(opStnMsrDTO.getSo2Flag())
                            .coFlag(opStnMsrDTO.getCoFlag())
                            .o3Flag(opStnMsrDTO.getO3Flag())
                            .no2Flag(opStnMsrDTO.getNo2Flag())
                            .pm10Flag(opStnMsrDTO.getPm10Flag())
                            .pm25Flag(opStnMsrDTO.getPm25Flag())
                            .allAir(opStnMsrDTO.getKhaiValue())
                            .updateTime(LocalDateTime.parse(opStnMsrDTO.getDataTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .build();
            dBrealtmDAO.save(new_station);
            return true;
        }
        else{
            return false;
        }
        //);
    }
    //측정소 대기정보 업데이트
    //측정소 대기정보 업데이트 시간 확인(for Compare)

}
