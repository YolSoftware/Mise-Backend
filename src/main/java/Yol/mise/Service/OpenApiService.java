package Yol.mise.Service;

import Yol.mise.Artifact.OPErrorCode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class OpenApiService {
    private UriComponents url_comp;
    private String json_string;
    private String raw_data;

    public int setRawData() throws IOException {
        URL url = new URL(url_comp.toString());
        HttpURLConnection url_conn = (HttpURLConnection) url.openConnection();
        url_conn.setRequestMethod("GET");
        if(url_conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(url_conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            raw_data = sb.toString();
            return 0;
        }
        else {
            raw_data = url_conn.getResponseMessage();
            return 1;
        }
    }

    public int setJsonString(String raw_string) {
        Gson gson = new Gson();
        try{
            JsonObject json = gson.fromJson(raw_string, JsonObject.class);
            json = gson.fromJson(json.get("response"), JsonObject.class);
            OPErrorCode error_code = gson.fromJson(json.get("header"), OPErrorCode.class);
            if (error_code.getResultCode() == 0) {
                json = gson.fromJson(json.get("body"), JsonObject.class);
                int data_count = Integer.parseInt(json.get("totalCount").toString());
                if (data_count > 0) {
                    json_string = json.get("items").toString();
                    return 0;
                }
                else {
                    //값이 아에 없을 경우 전부다 null로 처리
                    //TODO 값이 아에 없을 경우 가장 가까운곳을 찾아야지...
                    //LocalDateTime current = LocalDateTime.now();
                    //json_string = "{ \"so2Grade\":null,\"coFlag\":null,\"khaiValue\":null,\"so2Value\":null,\"coValue\":null,\"pm10Flag\":null,\"pm10Value\":null,\"o3Grade\":null,\"khaiGrade\":null,\"no2Flag\":null,\"no2Grade\":null,\"o3Flag\":null,\"so2Flag\":null,\"dataTime\":"+current.toString()+",\"coGrade\":null,\"no2Value\":null,\"pm10Grade\":null,\"o3Value\":null}";
                    return 1;
                }
            }
            else {
                return 2;
            }
        }catch (Exception e) {
            System.out.println(raw_string);
            return 100;
        }
    }

}
