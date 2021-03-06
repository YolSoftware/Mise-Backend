package Yol.mise.Artifact.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class OPStnMsrDTO {
    public OPStnMsrDTO() {
        so2Flag = null;
        so2Grade = -1;
        so2Value = -1;
        coFlag = null;
        coGrade = -1;
        coValue = -1;

        pm10Flag = null;
        pm10Grade = -1;
        pm10Value = -1;
        pm25Flag = null;
        pm25Grade = -1;
        pm25Value = -1;

        no2Flag = null;
        no2Grade = -1;
        no2Value = -1;

        o3Flag = null;
        o3Grade = -1;
        o3Value = -1;

        khaiGrade = -1;
        khaiValue = -1;
    }
    private int so2Grade;
    private String so2Flag;
    private float so2Value;

    private int coGrade;
    private String coFlag;
    private float coValue;

    private int pm10Grade;
    private String pm10Flag; // 측정소 고장 여부. 기본값 null
    private int pm10Value;

    private int pm25Grade;
    private String pm25Flag; // 상태정보(점검및교정,장비점검,자료이상,통신장애)
    private int pm25Value;

    private int no2Grade;
    private String no2Flag;
    private float no2Value;

    private int o3Grade;
    private String o3Flag;
    private float o3Value;

    private int khaiValue;
    private int khaiGrade;

    private String dataTime; // 오염도 측정 시간 (연-월-일)
}