package Yol.mise.Service;

import Yol.mise.Artifact.dto.OPStnMsrDTO;
import Yol.mise.Artifact.dto.OPWeekFrcstDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GetAirDataService {
    final String base_url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/";
    final String service_key = "X87euFz3fd072hiDInhC%2F%2BvESJAmhyTBt%2FfIQT0iLvZiC3UZEDAcVtSZxNUZqW9GVaaRi%2BaCeL1Oz7ss8Scklw%3D%3D";
    private Gson gson = new Gson();
    //
    public List<OPStnMsrDTO> callStnMsrApi(String stn_name) throws IOException {
        String encode_stn = URLEncoder.encode(stn_name, "UTF-8");
        String api_url = base_url + "getMsrstnAcctoRltmMesureDnsty";
        UriComponents uri_components = UriComponentsBuilder.fromHttpUrl(api_url)
                .queryParam("stationName", encode_stn)
                .queryParam("dataTerm", "daily")
                .queryParam("pageNo", "1")
                .queryParam("numOfRows", "1")
                .queryParam("returnType", "json")
                .queryParam("serviceKey", service_key)
                .queryParam("ver", "1.0")
                .build();
        OpenApiService service = new OpenApiService(uri_components, null, null);
        if (service.setRawData() == 0) {
            int set_json_code = service.setJsonString(service.getRaw_data());
            if (set_json_code == 0) {
                Type list_type = new TypeToken<ArrayList<OPStnMsrDTO>>(){}.getType();
                String json_string = service.getJson_string();
                String find_str = "\"-\"";
                Pattern pattern = Pattern.compile(find_str);

                Matcher m = pattern.matcher(json_string);
                json_string = m.replaceAll("-1");
                try {
                    List<OPStnMsrDTO> stn_msr_dtos = gson.fromJson(json_string, list_type);
                    return stn_msr_dtos;
                }catch(Exception e) {
                    System.out.println(service.getRaw_data());
                    return null;
                }
            } else if(set_json_code == 1) {
                List<OPStnMsrDTO> stn_msr_dtos = new ArrayList<>();
                OPStnMsrDTO stn_msr_dto = new OPStnMsrDTO();
                LocalDateTime current = LocalDateTime.now();
                stn_msr_dto.setDataTime(current.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                stn_msr_dtos.add(stn_msr_dto);
                return stn_msr_dtos;
            }

            else {
                System.out.println("IOException 에러");
                return null;
            }
        } else {
            System.out.println("Data가 없다.");
            return null;
        }
    }

    // TODO 주간 정보 데이터 받기 힘들어. String parsing도 해야댐. 시/도 별로 받기때문에 상명이가 해야됨.
    public List<OPWeekFrcstDTO> callWeekMsrApi(String data_time) throws IOException {
        String encode_time = URLEncoder.encode(data_time, "UTF-8");
        String api_url = base_url + "getMinuDustWeekFrcstDspth";
        UriComponents uri_components = UriComponentsBuilder.fromHttpUrl(api_url)
                .queryParam("searchDate", encode_time)
                .queryParam("returnType", "json")
                .queryParam("serviceKey", service_key)
                .queryParam("numOfRows","1")
                .queryParam("pageNo", "1")
                .build();
        OpenApiService service = new OpenApiService(uri_components, null, null);
        if (service.setRawData() == 0) {
            int set_json_code = service.setJsonString(service.getRaw_data());
            if (set_json_code == 0) {
                Type list_type = new TypeToken<ArrayList<OPWeekFrcstDTO>>() {}.getType();
                List<OPWeekFrcstDTO> week_dto = gson.fromJson(service.getJson_string(), list_type);
                return week_dto;
            } else {
                System.out.println("IOException 에러");
                return null;
            }
        }
        else {
            return null;
        }

    }
}
