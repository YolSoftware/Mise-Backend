package Yol.mise.Controller;

import Yol.mise.Artifact.dto.DBForecastViewDTO;
import Yol.mise.Artifact.dto.TEST;
import Yol.mise.ExternLibrary.GeoPoint;
import Yol.mise.Artifact.dao.DBStNmDAO;
import Yol.mise.Artifact.dto.OPNearStnDTO;
import Yol.mise.Artifact.dto.OPStnMsrDTO;
import Yol.mise.Service.*;
import Yol.mise.ExternLibrary.GeoTrans;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.classpath.ClassPathFileSystemWatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
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
    private DBrealtmService db_realtm_service;

    @Autowired
    private DBForecastViewService db_forecast_view_service;

    // GPS로 받아서
    // 1. tm값으로 바꾸고,
    // 2. 측정소 찾아서
    // 3. 측정소 API 검색
    // 4. return 측정 정보를 return. GPS 값을 보내줄 필요가 있냐.
    DBStNmDAO dbStNmDAO;

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
            TEST test = new TEST();
            List<OPStnMsrDTO> stn_msr_dto = get_air_data_service.callStnMsrApi(stn_name);

            test.setStationAddress(stn_address);
            test.setToday(stn_msr_dto.get(0));


            System.out.println(stn_name);

            try {
                Optional<DBForecastViewDTO> optional = db_forecast_view_service.findForecast(stn_name);
                DBForecastViewDTO data = optional.orElseThrow(NullPointerException::new);
                System.out.println(data.getSTATION_NAME() + ", " + data.getSTATION_LOCATION());

                System.out.println("db에 값이 있음");

            } catch (NullPointerException e) {
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
