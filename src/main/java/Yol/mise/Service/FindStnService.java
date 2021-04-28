package Yol.mise.Service;

import Yol.mise.Artifact.dto.OPNearCityStnDTO;
import Yol.mise.Artifact.dto.OPNearStnDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindStnService {
    final String base_url = "http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/";
    final String service_key = "X87euFz3fd072hiDInhC%2F%2BvESJAmhyTBt%2FfIQT0iLvZiC3UZEDAcVtSZxNUZqW9GVaaRi%2BaCeL1Oz7ss8Scklw%3D%3D";
    public Gson gson = new Gson();

    public List<OPNearStnDTO> callNearTMStnApi(String tm_x, String tm_y) throws IOException {
        String api_url = base_url + "getNearbyMsrstnList";
        UriComponents uri_components = UriComponentsBuilder.fromHttpUrl(api_url)
                .queryParam("tmX", tm_x)
                .queryParam("tmY",tm_y)
                .queryParam("returnType", "json")
                .queryParam("serviceKey", service_key)
                .build();

        System.out.println(uri_components.toString());

        OpenApiService helper = new OpenApiService(uri_components, null, null);
        helper.setRawData();
        helper.setJsonString(helper.getRaw_data());
        Type list_type = new TypeToken<ArrayList<OPNearStnDTO>>(){}.getType();
        List<OPNearStnDTO> near_stn_dto = gson.fromJson(helper.getJson_string(), list_type);

        return near_stn_dto;
    }

    public Object callNearCityStnAPi(@PathVariable String umd) throws IOException {
        String api_url = base_url + "getTMStdrCrdnt";
        String encode_umd = URLEncoder.encode(umd, "UTF-8");
        UriComponents uri_components = UriComponentsBuilder.fromHttpUrl(api_url)
                .queryParam("umdName", encode_umd)
                .queryParam("pageNo","1")
                .queryParam("numOfRows", "10")
                .queryParam("returnType", "json")
                .queryParam("serviceKey", service_key)
                .build();

        OpenApiService helper = new OpenApiService(uri_components, null, null);
        helper.setRawData();
        helper.setJsonString(helper.getRaw_data());
        Type list_type = new TypeToken<ArrayList<OPNearCityStnDTO>>(){}.getType();
        List<OPNearCityStnDTO> near_city_dto = gson.fromJson(helper.getJson_string(), list_type);

        return near_city_dto;
    }
}
