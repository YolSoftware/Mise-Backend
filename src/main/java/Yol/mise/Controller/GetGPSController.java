package Yol.mise.Controller;

import Yol.mise.Artifact.dto.*;
import Yol.mise.ExternLibrary.GeoPoint;
import Yol.mise.Service.*;
import Yol.mise.ExternLibrary.GeoTrans;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api", "/api/misenow"})
public class GetGPSController {
    int count = 0;

    private Gson gson;
    @Autowired
    private FindStnService find_stn_service;

    @Autowired
    private GetAirDataService get_air_data_service;

    @Autowired
    private DBtmafService db_forecast_view_service;

    @Autowired
    private DBrealtmService db_realtm_service;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/fineDust")
    public String dustData(@RequestBody @Validated GetLocation location) {
        count++;
        System.out.println("호출된 횟수 : " + count);
        gson = new Gson();
        try {
            System.out.println("위도 : " + location.latitude + ", 경도 : " + location.longitude);
            // 위도 경도를 tm좌표로 바꾸는 작업
            GeoPoint srcGeoPoint = new GeoPoint(Double.parseDouble(location.latitude), Double.parseDouble(location.longitude));
            GeoPoint resultPoint = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, srcGeoPoint);

            System.out.println("tm 변환 후 : " + resultPoint.x + ", " + resultPoint.y);

            String tm_x = String.valueOf(resultPoint.x);
            String tm_y = String.valueOf(resultPoint.y);

            // tm 좌표로 가까운 측정소 명을 가져오는 작업

            List<OPNearStnDTO> op_near_stn_dto = find_stn_service.callNearTMStnApi(tm_x, tm_y);
            String stn_name = op_near_stn_dto.get(0).getStationName();
            String stn_address = op_near_stn_dto.get(0).getAddr();

            // stn_name을 이용하여 DB에 접근하는 service
            PostAirDataDTO post_air_data = new PostAirDataDTO();
            post_air_data.setStationAddress(stn_address);

            try {
//                List<OPStnMsrDTO> optional1 = get_air_data_service.callStnMsrApi(stn_name);
//                OPStnMsrDTO data1 = optional1.get(0);

                Optional<DBrealtmDTO> optional1 = db_realtm_service.findStationAir(stn_name);
                DBrealtmDTO data1 = optional1.orElseThrow(NullPointerException::new);
                post_air_data.setToday(data1);

                Optional<DBtmafViewDTO> optional2 = db_forecast_view_service.findTmAfStation(stn_name);
                DBtmafViewDTO data2 = optional2.orElseThrow(NullPointerException::new);

                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("pm25Grade", Integer.toString(data2.getGradeTm()));
                post_air_data.setTomorrow(hashMap);

                hashMap.clear();
                hashMap.put("pm25Grade", Integer.toString(data2.getGradeAf()));
                post_air_data.setDayAfterTommorow(hashMap);

                System.out.println(data2.getStationName() + ", " + data2.getStationLocation());
                System.out.println("db에 값이 있음");
                return gson.toJson(post_air_data);
            } catch (Exception e) {
                System.out.println("db에 값이 없음");
                // DB에 최신 값이 없으면 API 호출

            }

            return gson.toJson(post_air_data);
        } catch (IOException e) {
            System.out.println("IOException 에러");
            return "{ errorMsg : IOExecption 에러, errorCode : 000}";
        }
    }

    @Data
    static class GetLocation {
        public String latitude; // 위도
        public String longitude;   // 경도
    }
}
