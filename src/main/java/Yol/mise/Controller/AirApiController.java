package Yol.mise.Controller;

import Yol.mise.Artifact.dto.OPStnMsrDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AirApiController {

    @GetMapping("/stnmsrapi")
    public String callStMsrApi() throws IOException {

        String url_str = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/" +
                "getMsrstnAcctoRltmMesureDnsty" +
                "?stationName=%EC%A2%85%EB%A1%9C%EA%B5%AC" +
                "&dataTerm=month" +
                "&pageNo=1" +
                "&numOfRows=1" +
                "&returnType=json" +
                "&serviceKey=vzt9ZGiRSLznVI%2FpSr%2BL4Lth9rr8jHKqp4RRyDX0cvUfeiA4w6Chfr1hyGaX%2FGsXuxNH22VKBgxq8vY6svNLNw%3D%3D";
        URL url = new URL(url_str);
        HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();
        url_connection.setRequestMethod("GET");
        OPStnMsrDTO stn_msr = new OPStnMsrDTO();


        if (url_connection.getResponseCode() == 200) {
            // JACSON 라이브러리를 이용한 JSON PARSING
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> raw_json = mapper.readValue(url_connection.getInputStream(), Map.class);
            System.out.println(raw_json);

            Map<String, Object> tmp = (Map<String, Object>) raw_json.get("response");
            tmp = (Map<String, Object>) tmp.get("body");

            //stn_msr = mapper.readValue(items, OPStnMsrDTO.class);
            System.out.println(tmp);

        }
        /*
"items":[{"so2Grade":"1","coFlag":null,"khaiValue":"73","so2Value":"0.004","coValue":"0.4","pm10Flag":null,"pm10Value":"36",
"o3Grade":"2","khaiGrade":"2","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2021-04-09 17:00",
"coGrade":"1","no2Value":"0.014","pm10Grade":"2","o3Value":"0.058"}],"pageNo":1,"numOfRows":1}}
         */
        return "0";
    }

}
