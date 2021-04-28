package Yol.mise.Service;

import Yol.mise.Artifact.dao.DBStinfoDAO;
import Yol.mise.Artifact.dto.DBStinfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DBStinfoService {
    @Autowired
    private DBStinfoDAO dbStinfoDAO; //repository

    //특정 측정소 조회
    public Optional<DBStinfoDTO> findStation(String stname) throws Exception {
        final Optional<DBStinfoDTO> dbStinfoDTOOptional = dbStinfoDAO.findByStationName(stname);
        return dbStinfoDTOOptional;
    }

    //측정소의 모든 정보가 반환
    public List<DBStinfoDTO> findAllStation() throws Exception{
        final List<DBStinfoDTO> dbStinfoDTOOptional = dbStinfoDAO.findAll();
        return dbStinfoDTOOptional;
    }

    //측정소(station_name) 모든 이름 반환
    /*public List<Stirng> findAllStationName() throws Exception{
        final List<DBStinfoDTO> dbStinfoDTOList = dbStinfoDAO.findAll();
        return dbStinfoDTOList.
    }*/

    //측정소 새로 만들기
    @Transactional
    public boolean createStation(String stname,
                                String stlocation,
                                float stx,
                                float sty){
        Optional<DBStinfoDTO> dbStinfoDTOOptional = dbStinfoDAO.findByStationName(stname);
        if(!dbStinfoDTOOptional.isPresent()){
            DBStinfoDTO new_station =
                    DBStinfoDTO.builder()
                            .stationName(stname)
                            .stationLocation(stlocation)
                            .stationX(stx)
                            .stationY(sty)
                            .build();
            dbStinfoDAO.save(new_station);
            return true;
        }
        else {
            return false;
        }
    }
    //기존 측정소 정보 수정
    @Transactional
    public boolean updateStation(String stname,
                          float stx,
                          float sty) throws Exception{
        Optional<DBStinfoDTO> dbStinfoDTOOptional = dbStinfoDAO.findByStationName(stname);

        //station_info.ifPresent(
        if(dbStinfoDTOOptional.isPresent()) {
            DBStinfoDTO new_station =
                    DBStinfoDTO.builder()
                            .stationNumber(dbStinfoDTOOptional.get().stationNumber) //중복되는 값으로 인식하면 insert 두번으로 인식하여 오류남
                            .stationName(stname)
                            .stationLocation(dbStinfoDTOOptional.get().stationLocation)
                            .stationX(stx)
                            .stationY(sty)
                            .build();
            dbStinfoDAO.save(new_station);
            return true;
        }
        else{
            return false;
        }
        //);
    }

    //측정소 정보 제거
    @Transactional
    public boolean deleteStation(String stname){
        Optional<DBStinfoDTO> dbStinfoDTOOptional = dbStinfoDAO.findByStationName(stname);
        dbStinfoDTOOptional.ifPresent(
                selectStation->{
                    dbStinfoDAO.deleteByStationName(stname);
                });
        Optional<DBStinfoDTO> deleteStation = dbStinfoDAO.findByStationName(stname);
        if(!deleteStation.isPresent()){
            return true;
        }
        else{
            return false;
        }
    }


}
