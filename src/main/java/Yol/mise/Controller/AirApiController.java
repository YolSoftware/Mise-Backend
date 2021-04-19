package Yol.mise.Controller;

import Yol.mise.Artifact.dto.OPStnMsrDTO;
import Yol.mise.Artifact.dto.OPWeekFrcstDTO;
import Yol.mise.OpenApiHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/airapi")
public class AirApiController {
    final String base_url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/";
    final String service_key = "X87euFz3fd072hiDInhC%2F%2BvESJAmhyTBt%2FfIQT0iLvZiC3UZEDAcVtSZxNUZqW9GVaaRi%2BaCeL1Oz7ss8Scklw%3D%3D";
    private Gson gson = new Gson();

    public String httpConnection(URL url) throws IOException {
        HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();
        url_connection.setRequestMethod("GET");
        String raw_string = "";
        if(url_connection.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(url_connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            raw_string = sb.toString();
        }
        return raw_string;
    }

    @GetMapping("/stnmsr/{stn}")
    public Object callStnMsrApi(@PathVariable String stn) throws IOException{
        String encode_stn = URLEncoder.encode(stn, "UTF-8");
        String api_url = base_url + "getMsrstnAcctoRltmMesureDnsty";
        UriComponents uri_components = UriComponentsBuilder.fromHttpUrl(api_url)
                .queryParam("stationName", encode_stn)
                .queryParam("dataTerm","daily")
                .queryParam("pageNo","1")
                .queryParam("numOfRows","1")
                .queryParam("returnType","json")
                .queryParam("serviceKey",service_key)
                .queryParam("ver","1.0")
                .build();
        OpenApiHelper helper = new OpenApiHelper(uri_components, null, null);
        if( helper.setRawData() == 0) {
            if (helper.setJsonString(helper.getRaw_data()) == 0) {
                Type list_type = new TypeToken<ArrayList<OPStnMsrDTO>>(){}.getType();
                List<OPStnMsrDTO> stn_msr_dto = gson.fromJson(helper.getJson_string(), list_type);
                return stn_msr_dto;
            }
            else {
                return "";
            }
        }
        return "";
    }

    @GetMapping("/week")
    public Object callWeekFrcstApi() throws IOException {
        JsonObject raw_json;
        Gson gson = new Gson();
        int data_count = 0;

        String api_url = base_url + "getMinuDustWeekFrcstDspth";
        LocalDate cur_date = LocalDate.now();
        LocalTime cur_time = LocalTime.now();
        LocalTime base_time = LocalTime.of(18,00,00,00);

        if (cur_time.isAfter(base_time)) {
            String enc_date = URLEncoder.encode(cur_date.toString(), "UTF-8");
            UriComponents uri_components = UriComponentsBuilder.fromHttpUrl(api_url)
                    .queryParam("searchDate", enc_date)
                    .queryParam("returnType","json")
                    .queryParam("serviceKey",service_key)
                    .queryParam("numOfRows","1")
                    .queryParam("pageNo","1")
                    .build();

            URL url = new URL(uri_components.toString());
            String raw_string = httpConnection(url);
            raw_json = gson.fromJson(raw_string, JsonObject.class);
            raw_json = gson.fromJson(raw_json.get("response"), JsonObject.class);
            raw_json = gson.fromJson(raw_json.get("body"), JsonObject.class);
            data_count = Integer.parseInt(raw_json.get("totalCount").toString());

            if (data_count > 0) {
                Type list_type = new TypeToken<ArrayList<OPWeekFrcstDTO>>(){}.getType();
                List<OPWeekFrcstDTO> week_frcst_dto = gson.fromJson(raw_json.get("items").toString(), list_type);;
                return week_frcst_dto;
            } else {
                return "아직 측정되지 않았습니다.";
            }
        }

        else {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(headers);
            LocalDate test = LocalDate.of(2021,04,13);
            String enc_date = URLEncoder.encode(test.toString(), "UTF-8");
            UriComponents uri_components = UriComponentsBuilder.fromHttpUrl(api_url)
                    .queryParam("searchDate", enc_date)
                    .queryParam("returnType","json")
                    .queryParam("serviceKey",service_key)
                    .queryParam("numOfRows","1")
                    .queryParam("pageNo","1")
                    .build();

            URL url = new URL(uri_components.toString());
            System.out.println(url.toString());
            String raw_string = httpConnection(url);
            raw_json = gson.fromJson(raw_string, JsonObject.class);
            raw_json = gson.fromJson(raw_json.get("response"), JsonObject.class);
            raw_json = gson.fromJson(raw_json.get("body"), JsonObject.class);
            data_count = Integer.parseInt(raw_json.get("totalCount").toString());

            if (data_count > 0) {
                Type listType = new TypeToken<ArrayList<OPWeekFrcstDTO>>(){}.getType();
                List<OPWeekFrcstDTO> week_frcst_dto = gson.fromJson(raw_json.get("items").toString(), listType);;
                return week_frcst_dto;
            } else {
                return "이건 무슨에러일까?";
            }
            //return "18시 이후에 다시 시도하세요";
        }
    }
}
