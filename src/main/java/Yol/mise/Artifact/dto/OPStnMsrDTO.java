package Yol.mise.Artifact.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OPStnMsrDTO {
    private String so2Grade;
    private String so2Flag;
    private String so2Value;

    private String coGrade;
    private String coFlag;
    private String coValue;

    private String pm10Grade;
    private String pm10Flag;
    private String pm10Value;

    private String pm25Grade;
    private String pm25Flag;
    private String pm25Value;

    private String no2Grade;
    private String no2Flag;
    private String no2Value;

    private String o3Grade;
    private String o3Flag;
    private String o3Value;

    private String khaiValue;
    private String khaiGrade;

    private String dataTime;
}