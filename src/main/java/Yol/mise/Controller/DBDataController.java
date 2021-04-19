package Yol.mise.Controller;

import Yol.mise.Artifact.dao.DBStinfoDAO;
import Yol.mise.Artifact.dto.DBStinfoDTO;
import com.sun.jdi.event.ExceptionEvent;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Yol.mise.Artifact.dao.DBStNmDAO;
import Yol.mise.Artifact.dto.DBStNmDTO;

import java.util.List;
import java.util.Optional;

//
@RestController
public class DBDataController{
    @Autowired
    private DBStinfoDAO dbStinfoDAO; //repository

    //특정 측정소 조회
    @GetMapping(value = "/station/{param}")
    public Optional<DBStinfoDTO> select(@PathVariable Long param) throws Exception {
        final Optional<DBStinfoDTO> station_info = dbStinfoDAO.findById(param);
        return station_info;
    }
    //station의 모든 정보가 반환
    @GetMapping(value = "/allStation")
    public Optional<DBStinfoDTO> selectAll() throws Exception{
        //final Optional<DBStinfoDTO> station_info = dbStinfoDAO.findAllById();
        //return station_info;
        return null;
    }

    //select문 측정소명을 받아와서 join(station_info:측정소명, station_realtm:측정소명) 대기정보를 조회
    //+환경정보가 null이거나 최신정보가 아닐 때 update

    //update문 측정소명을 특정지어서 realtime 정보 update

    //측정소 새로 만들기
    @GetMapping(value = "/stationCreate")
    public Optional<DBStinfoDTO> create(){
        return null;
    }

    //기존 측정소 정보 수정
    @GetMapping(value = "/stationUpdate/{param}/{stname}/{stlocation}/{stx}/{sty}")
    public void update(@PathVariable Long param,
                       @PathVariable String stname,
                       @PathVariable String stlocation,
                       @PathVariable float stx,
                       @PathVariable float sty) throws Exception{
        Optional<DBStinfoDTO> station_info = dbStinfoDAO.findById(param);
        //station_info.ifPresent(
                DBStinfoDTO new_station =
                        DBStinfoDTO.builder()
                                .station_number(param)
                                .station_name(stname)
                                .station_location(stlocation)
                                .station_x(stx)
                                .station_y(sty)
                                .build();
                dbStinfoDAO.save(new_station);

        //);
    }

}
