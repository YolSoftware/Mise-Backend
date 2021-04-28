package Yol.mise.Controller;

import Yol.mise.Artifact.dto.DBtmafViewDTO;
import Yol.mise.Artifact.dto.PostAirDataDTO;
import Yol.mise.ExternLibrary.GeoPoint;
import Yol.mise.Artifact.dto.OPNearStnDTO;
import Yol.mise.Artifact.dto.OPStnMsrDTO;
import Yol.mise.Service.*;
import Yol.mise.ExternLibrary.GeoTrans;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api", "/api/misenow"})
public class GetGPSController {
    int count = 0;
    private Gson gson = new Gson();

    @Autowired
    private FindStnService find_stn_service;

    @Autowired
    private GetAirDataService get_air_data_service;

    @Autowired
    private DBtmafService db_forecast_view_service;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/fineDust")
    public String dustData(@RequestBody @Validated GetLocation location) {
        count++;
        System.out.println("호출된 횟수 : " + count);
        try {
            System.out.println("위도 : " + location.latitude + ", 경도 : " + location.longitude);
            // 위도 경도를 tm좌표로 바꾸는 작업
            GeoPoint srcGeoPoint = new GeoPoint(Double.parseDouble(location.latitude), Double.parseDouble(location.longitude));
            GeoPoint resultPoint = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, srcGeoPoint);

            System.out.println(resultPoint.x + ", " + resultPoint.y);

            String tm_x = String.valueOf(resultPoint.x);
            String tm_y = String.valueOf(resultPoint.y);

            // tm 좌표로 가까운 측정소 명을 가져오는 작업

            List<OPNearStnDTO> op_near_stn_dto = find_stn_service.callNearTMStnApi(tm_x, tm_y);
            String stn_name = op_near_stn_dto.get(0).getStationName();
            String stn_address = op_near_stn_dto.get(0).getAddr();

            // stn_name을 이용하여 DB에 접근하는 service
            PostAirDataDTO test = new PostAirDataDTO();
            List<OPStnMsrDTO> stn_msr_dto = get_air_data_service.callStnMsrApi(stn_name);

            test.setStationAddress(stn_address);
            test.setToday(stn_msr_dto.get(0));


            System.out.println(stn_name);

            try {
                Optional<DBtmafViewDTO> optional = db_forecast_view_service.findTmAfStation(stn_name);
                DBtmafViewDTO data = optional.orElseThrow(NullPointerException::new);
                System.out.println(data.getStationName() + ", " + data.getStationLocation());

                System.out.println("db에 값이 있음");

            } catch (Exception e) {
                System.out.println("db에 값이 없음");
                // DB에 최신 값이 없으면 API 호출

            }

            return gson.toJson(test);
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
