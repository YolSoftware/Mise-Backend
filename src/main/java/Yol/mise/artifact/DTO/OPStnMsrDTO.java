package Yol.mise.artifact.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OPStnMsrDTO {
    private String date_time;

    private String so2_flag;
    private String so2_grade;
    private String so2_value;

    private String co_flag;
    private String co_grade;
    private String co_value;

    private String pm10_flag;
    private String pm10_grade;
    private String pm10_value;

    private String khai_flag;
    private String khai_value;

    private String o3_flag;
    private String o3_grade;
    private String o3_value;

    private String no2_flag;
    private String no2_grade;
    private String no2_value;

}

        /*

    {"so2Grade":"1","coFlag":null,"khaiValue":"73","so2Value":"0.004","coValue":"0.4",
    "pm10Flag":null,"pm10Value":"36","o3Grade":"2","khaiGrade":"2","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2021-04-09 17:00","coGrade":"1","no2Value":"0.014","pm10Grade":"2","o3Value":"0.058"}],"pageNo":1,"numOfRows":1},"header":{"resultMsg":"NORMAL_CODE","resultCode":"00"}}}
         */