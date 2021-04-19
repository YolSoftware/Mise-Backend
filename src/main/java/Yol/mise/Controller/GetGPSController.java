package Yol.mise.Controller;

import Yol.mise.Artifact.dao.DBStNmDAO;
import Yol.mise.Artifact.dto.DBStNmDTO;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping({"/api", "/api/misenow"})
public class GetGPSController {
    int count = 0;

    DBStNmDAO dbStNmDAO;
    @PostMapping ("/location")
    public String getPostLocation (@RequestBody @Validated GetLocation location) {
        System.out.println("위도 : " + location.latitude + " 경도 : " + location.longitude);
        return "위도 : " + location.latitude + " 경도 : " + location.longitude;
    }

    @GetMapping("/location")
    public String getLocation (@RequestBody @Validated GetLocation location) {
        System.out.println("위도 : " + location.latitude + " 경도 : " + location.longitude);
        return "위도 : " + location.latitude + " 경도 : " + location.longitude;
    }

    @GetMapping("/ip")
    public ResponseEntity<String> ip (HttpServletRequest request) {
        // 요청을 보낸 클라이언트의 IP주소를 반환합니다.
        System.out.println(request.getRemoteAddr());
        return ResponseEntity.ok(request.getRemoteAddr());
    }

    @GetMapping("/test")
    public String testMessage () {
        System.out.println("욜꾤쑐뾸쬴뚈뚈ㄲ뚈");
        return "욜꾤쑐뾸쬴뚈뚈ㄲ뚈ㄹ";
    }

    @ResponseBody
    @GetMapping("/fineDust")
    public String dustData () {
        DBStNmDTO dbStNmDTO = new DBStNmDTO("2", "2", "2", "2", "2");
        List<DBStNmDTO> testList;
        try{
            System.out.println("1");
            testList = dbStNmDAO.selectUsers(dbStNmDTO);
            System.out.println("2");
            if(testList.size() > 0) {
                for (DBStNmDTO t : testList) {
                    System.out.println(t.getStation_x());
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }

        count++;

        JsonObject nowData = new JsonObject();

        JsonObject tomorrowData = new JsonObject();
        JsonObject dayAfterTommorowData = new JsonObject();
        JsonObject object = new JsonObject();
        nowData.addProperty("pm25Value", "오늘 초미세먼지");
        nowData.addProperty("pm100Value", "오늘 미세먼지");
        nowData.addProperty("o3Value", "오늘 오존");
        nowData.addProperty("no2Value", "오늘 이산화질소");
        nowData.addProperty("coValue", "오늘 일산화탄소");
        nowData.addProperty("so2Value", "오늘 아황산가스");
        object.add("now", nowData);

        tomorrowData.addProperty("pm25Value", "내일 초미세먼지");
        tomorrowData.addProperty("pm100Value", "내일 미세먼지");
        tomorrowData.addProperty("o3Value", "내일 오존");
        tomorrowData.addProperty("no2Value", "내일 이산화질소");
        tomorrowData.addProperty("coValue", "내일 일산화탄소");
        tomorrowData.addProperty("so2Value", "내일 아황산가스");
        object.add("tomorrow", tomorrowData);

        dayAfterTommorowData.addProperty("pm25Value", "주말 초미세먼지");
        dayAfterTommorowData.addProperty("pm100Value", "주말 미세먼지");
        dayAfterTommorowData.addProperty("o3Value", "주말 오존");
        dayAfterTommorowData.addProperty("no2Value", "주말 이산화질소");
        dayAfterTommorowData.addProperty("coValue", "주말 일산화탄소");
        dayAfterTommorowData.addProperty("so2Value", "주말 아황산가스");
        object.add("dayAfterTommorow", dayAfterTommorowData);

        System.out.println("먼지 데이터 총 : " + count + "번 보냄");
        return object.toString();
        //return "먼지 데이터 : " + count;
    }

    @Data
    static class GetLocation {
        public String latitude; // 위도
        public String longitude;   // 경도
    }
}
