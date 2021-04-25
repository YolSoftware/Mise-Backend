package Yol.mise.Controller;

import Yol.mise.Artifact.dao.DBStinfoDAO;
import Yol.mise.Artifact.dto.DBStinfoDTO;
import Yol.mise.Service.DBStinfoService;
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
public class DBTestController {
    @Autowired
    private DBStinfoDAO dbStinfoDAO; //repository

    @Autowired
    private DBStinfoService dbStinfoService;


    //select문 측정소명을 받아와서 join(station_info:측정소명, station_realtm:측정소명) 대기정보를 조회
    //+환경정보가 null이거나 최신정보가 아닐 때 update

    //update문 측정소명을 특정지어서 realtime 정보 update

    //특정 측정소 조회
    @GetMapping(value = "/station/{stname}")
    public Optional<DBStinfoDTO> select(@PathVariable String stname) throws Exception {
        //final Optional<DBStinfoDTO> station_info = dbStinfoDAO.findByStationName(stname);
        return dbStinfoService.findStation(stname);
        //return station_info;
    }
    //측정소의 모든 정보가 반환
    @GetMapping(value = "/allStation")
    public List<DBStinfoDTO> selectAll() throws Exception{
        //final List<DBStinfoDTO> station_info = dbStinfoDAO.findAll();
        return dbStinfoService.findAllStation();
    }

    //측정소 새로 만들기
    @GetMapping(value = "/stationCreate/{stname}/{stlocation}/{stx}/{sty}")
    public String create(@PathVariable String stname,
                          @PathVariable String stlocation,
                          @PathVariable float stx,
                          @PathVariable float sty){
        if( dbStinfoService.createStation(stname, stlocation,stx,sty) ){
            System.out.println("Station Name : " + stname + "  : 측정소에 대한 데이터 삽입에 성공하였습니다.");
            return stname + " 측정소에 대한 데이터 삽입에 성공하였습니다.";
        }
        else{
            System.out.println("Station Name : " + stname + "  : 데이터 삽입에 실패하였습니다.");
            return stname +  "데이터 삽입에 실패하였습니다.";
        }

    }

    //기존 측정소 정보 수정
    @GetMapping(value = "/stationUpdate/{stname}/{stlocation}/{stx}/{sty}")
    public String update(@PathVariable String stname,
                       @PathVariable String stlocation,
                       @PathVariable float stx,
                       @PathVariable float sty) throws Exception{
        //station_info.ifPresent(
        if( dbStinfoService.updateStation(stname, stlocation, stx, sty)) {
            System.out.println("Station Name : " + stname + "  : 측정소에 대한 데이터 수정에 성공하였습니다.");
            return stname + " 측정소에 대한 데이터 수정에 성공하였습니다.";
            
        }
        else{
            System.out.println("Station Name : " + stname + "  : 데이터 수정에 실패하였습니다.");
            return stname +  "데이터 수정에 실패하였습니다.";
        }
        //);
    }

    @GetMapping(value = "/stationDelete/{stname}")
    public String delete(@PathVariable String stname){;
        if( dbStinfoService.deleteStation(stname)){
            System.out.println("Station Name : " + stname + "  : 측정소에 대한 데이터 삭제에 성공하였습니다.");
            return stname + " 측정소에 대한 데이터 삭제에 성공하였습니다.";
        }
        else{
            System.out.println("Station Name : " + stname + "  : 데이터 삭제에 실패하였습니다.");
            return stname +  "데이터 삭제에 실패하였습니다.";
        }
    }


}
