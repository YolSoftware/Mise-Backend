package Yol.mise.Controller;

import Yol.mise.Artifact.OPErrorCode;
import Yol.mise.Artifact.dto.OPNearCityStnDTO;
import Yol.mise.Artifact.dto.OPNearStnDTO;
import Yol.mise.OpenApiHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stnapi")
public class StnApiController {
    final String base_url = "http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/";
    final String service_key = "X87euFz3fd072hiDInhC%2F%2BvESJAmhyTBt%2FfIQT0iLvZiC3UZEDAcVtSZxNUZqW9GVaaRi%2BaCeL1Oz7ss8Scklw%3D%3D";
    public Gson gson = new Gson();
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

    @GetMapping("nearstn/tmx/{tm_x}/tmy/{tm_y}")
    public Object callNearTMStnApi(@PathVariable String tm_x, @PathVariable String tm_y) throws IOException {
        String api_url = base_url + "getNearbyMsrstnList";
        UriComponents uri_components = UriComponentsBuilder.fromHttpUrl(api_url)
                .queryParam("tmX", tm_x)
                .queryParam("tmY",tm_y)
                .queryParam("returnType", "json")
                .queryParam("serviceKey", service_key)
                .build();
        OpenApiHelper helper = new OpenApiHelper(uri_components, null, null);
        helper.setRawData();
        helper.setJsonString(helper.getRaw_data());
        Type list_type = new TypeToken<ArrayList<OPNearStnDTO>>(){}.getType();
        List<OPNearStnDTO> near_stn_dto = gson.fromJson(helper.getJson_string(), list_type);

        return near_stn_dto;
    }

    @GetMapping("nearstn/umd/{umd}")
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

        OpenApiHelper helper = new OpenApiHelper(uri_components, null, null);
        helper.setRawData();
        helper.setJsonString(helper.getRaw_data());
        Type list_type = new TypeToken<ArrayList<OPNearCityStnDTO>>(){}.getType();
        List<OPNearCityStnDTO> near_city_dto = gson.fromJson(helper.getJson_string(), list_type);

        return near_city_dto;
    }
}
