package Yol.mise.Controller;

import Yol.mise.Artifact.dao.DBStinfoDAO;
import Yol.mise.Artifact.dto.DBStinfoDTO;
import com.sun.jdi.event.ExceptionEvent;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Yol.mise.Artifact.dao.DBStNmDAO;
import Yol.mise.Artifact.dto.DBStNmDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//
@RestController
public class DBDataController{
    @Autowired
    private DBStinfoDAO dbStinfoDAO; //repository

    //select문 측정소명을 받아와서 join(station_info:측정소명, station_realtm:측정소명) 대기정보를 조회
    //+환경정보가 null이거나 최신정보가 아닐 때 update

    //update문 측정소명을 특정지어서 realtime 정보 update

    //특정 측정소 조회
    @GetMapping(value = "/station/{stname}")
    public Optional<DBStinfoDTO> select(@PathVariable String stname) throws Exception {
        final Optional<DBStinfoDTO> station_info = dbStinfoDAO.findByStationName(stname);
        return station_info;
    }
    //측정소의 모든 정보가 반환
    @GetMapping(value = "/allStation")
    public List<DBStinfoDTO> selectAll() throws Exception{
        final List<DBStinfoDTO> station_info = dbStinfoDAO.findAll();
        return station_info;
    }

    //측정소 새로 만들기
    @GetMapping(value = "/stationCreate/{stname}/{stlocation}/{stx}/{sty}")
    public boolean create(@PathVariable String stname,
                          @PathVariable String stlocation,
                          @PathVariable float stx,
                          @PathVariable float sty){
        Optional<DBStinfoDTO> station_info = dbStinfoDAO.findByStationName(stname);
        if(!station_info.isPresent()){
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
    @GetMapping(value = "/stationUpdate/{stname}/{stlocation}/{stx}/{sty}")
    public boolean update(@PathVariable String stname,
                       @PathVariable String stlocation,
                       @PathVariable float stx,
                       @PathVariable float sty) throws Exception{
        Optional<DBStinfoDTO> station_info = dbStinfoDAO.findByStationName(stname);

        //station_info.ifPresent(
        if(station_info.isPresent()) {
            DBStinfoDTO new_station =
                    DBStinfoDTO.builder()
                            .stationNumber(station_info.get().stationNumber) //중복되는 값으로 인식하면 insert 두번으로 인식하여 오류남
                            .stationName(stname)
                            .stationLocation(stlocation)
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

    @Transactional
    @GetMapping(value = "/stationDelete/{stname}")
    public boolean delete(@PathVariable String stname){
        Optional<DBStinfoDTO> station_info = dbStinfoDAO.findByStationName(stname);
        station_info.ifPresent(
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
