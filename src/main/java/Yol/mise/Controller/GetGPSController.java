package Yol.mise.Controller;

import Yol.mise.ExternLibrary.GeoPoint;
import Yol.mise.Artifact.dao.DBStNmDAO;
import Yol.mise.Artifact.dto.OPNearStnDTO;
import Yol.mise.Artifact.dto.OPStnMsrDTO;
import Yol.mise.Service.FindStnService;
import Yol.mise.ExternLibrary.GeoTrans;
import Yol.mise.Service.GetAirDataService;
import Yol.mise.Service.OpenApiService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
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

@RestController
@RequestMapping({"/api", "/api/misenow"})
public class GetGPSController {
    int count = 0;
    final String base_url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/";
    final String service_key = "X87euFz3fd072hiDInhC%2F%2BvESJAmhyTBt%2FfIQT0iLvZiC3UZEDAcVtSZxNUZqW9GVaaRi%2BaCeL1Oz7ss8Scklw%3D%3D";
    private Gson gson = new Gson();


    // GPS로 받아서
    // 1. tm값으로 바꾸고,
    // 2. 측정소 찾아서
    // 3. 측정소 API 검색
    // 4. return 측정 정보를 return. GPS 값을 보내줄 필요가 있냐.
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
    public String dustData (@RequestBody @Validated GetLocation location) {
        GeoPoint srcGeoPoint = new GeoPoint(Double.parseDouble(location.latitude), Double.parseDouble(location.longitude));
        GeoPoint resultPoint = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, srcGeoPoint);

        String tm_x = String.valueOf(resultPoint.x);
        String tm_y = String.valueOf(resultPoint.y);

        try{
            List<OPNearStnDTO> op_near_stn_dto;
            FindStnService find_stn_service = new FindStnService();
            op_near_stn_dto = find_stn_service.callNearTMStnApi(tm_x, tm_y);
            String stn_name = op_near_stn_dto.get(0).getStationName();
            // stn_name을 이용하여 DB에 접근하는 service

            // DB에 최신 값이 없으면 API 호출
            List<OPStnMsrDTO> stn_msr_dto;
            GetAirDataService get_air_data_service = new GetAirDataService();
            stn_msr_dto = get_air_data_service.callStnMsrApi(stn_name);
            // 새로운 값을 DB에 저장 하는 service

            return gson.toJson(stn_msr_dto.get(0));
        }
        catch (IOException e) {
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
