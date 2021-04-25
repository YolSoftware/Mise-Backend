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
    private String pm10Flag; // 측정소 고장 여부. 기본값 null
    private String pm10Value;

    private String pm25Grade;
    private String pm25Flag; // 상태정보(점검및교정,장비점검,자료이상,통신장애)
    private String pm25Value;

    private String no2Grade;
    private String no2Flag;
    private String no2Value;

    private String o3Grade;
    private String o3Flag;
    private String o3Value;

    private String khaiValue;
    private String khaiGrade;

    private String dataTime; // 오염도 측정 시간 (연-월-일)
}