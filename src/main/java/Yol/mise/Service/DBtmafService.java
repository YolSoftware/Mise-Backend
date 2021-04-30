package Yol.mise.Service;

import Yol.mise.Artifact.dao.DBtmafDAO;
import Yol.mise.Artifact.dao.DBtmafViewDAO;
import Yol.mise.Artifact.dto.DBStinfoDTO;
import Yol.mise.Artifact.dto.DBtmafDTO;
import Yol.mise.Artifact.dto.DBtmafViewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DBtmafService {

    @Autowired
    private DBtmafDAO dBtmafDAO;
    @Autowired
    private DBtmafViewDAO dBtmafViewDAO;

    //특정 위치 내일,모레 대기 정보 조회(station_location[ex)서울]이 특정될때)
    public Optional<DBtmafDTO> findTmAfLocation(String stlocation) throws Exception {
        final Optional<DBtmafDTO> dBtmafDTOOptional = dBtmafDAO.findById(stlocation);
        return dBtmafDTOOptional;
    }

    //특정 위치 내일,모레 대기 정보 조회(station_name이 특정될때)
    public Optional<DBtmafViewDTO> findTmAfStation(String stname) throws Exception{
        final Optional<DBtmafViewDTO> dBtmafViewDTOOptional = dBtmafViewDAO.findByStationName(stname);
        return dBtmafViewDTOOptional;
    }

    //모든 위치 내일,모레 대기 정보 조회
    public List<DBtmafDTO> findAllTmAfLocation() throws Exception {
        final List<DBtmafDTO> dBtmafDTOOptional = dBtmafDAO.findAll();
        return dBtmafDTOOptional;
    }

    //측정소 위치 (station_location) 모든 이름 반환
    public List<Object> findAllLocation() throws Exception{
        final List<Object> dbStlocationDTOList = dBtmafDAO.findAllStationLocation();
        return dbStlocationDTOList;

    }

    @Transactional
    //특정위치 내일,모레 대기정보 업데이트
    public boolean UpdateTmAfLocation(String stlocation,
                                      String gradeTm,
                                      String gradeAf,
                                      LocalDateTime updateTime) throws Exception{
        final Optional<DBtmafDTO> dBtmafDTOOptional = dBtmafDAO.findById(stlocation);


        if(dBtmafDTOOptional.isPresent()) {
            DBtmafDTO new_tmaf =
                    DBtmafDTO.builder()
                            .stationLocation(dBtmafDTOOptional.get().stationLocation) //중복되는 값으로 인식하면 insert 두번으로 인식하여 오류남
                            .gradeTm(gradeTm)
                            .gradeAf(gradeAf)
                            .updateTime(updateTime)
                            .build();
            dBtmafDAO.save(new_tmaf);
            return true;
        }
        else{
            return false;
        }


    }

}
