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
import java.util.ArrayList;
import java.util.List;

@Service
public class GetAirDataService {
    final String base_url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/";
    final String service_key = "X87euFz3fd072hiDInhC%2F%2BvESJAmhyTBt%2FfIQT0iLvZiC3UZEDAcVtSZxNUZqW9GVaaRi%2BaCeL1Oz7ss8Scklw%3D%3D";
    private Gson gson = new Gson();

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
        OpenApiService helper = new OpenApiService(uri_components, null, null);
        if (helper.setRawData() == 0) {
            if (helper.setJsonString(helper.getRaw_data()) == 0) {
                Type list_type = new TypeToken<ArrayList<OPStnMsrDTO>>() {
                }.getType();
                List<OPStnMsrDTO> stn_msr_dto = gson.fromJson(helper.getJson_string(), list_type);
                return stn_msr_dto;
            } else {
                System.out.println("IOException 에러");
                return null;
            }
        } else {
            return null;
        }
    }
    // TODO 주간 정보 데이터 받기 힘들어. String parsing도 해야댐. 시/도 별로 받기때문에 상명이가 해야됨.
    //public List<OPWeekFrcstDTO> callWeekMsrApi(String)
}
