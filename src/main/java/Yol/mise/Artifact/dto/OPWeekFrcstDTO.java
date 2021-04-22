package Yol.mise.Artifact.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OPWeekFrcstDTO {
    // 서울 : 낮음, 인천 : 낮음, 경기북부 ....
    // 초 미세먼지.
    // 2등급(좋음/나쁨)
    private String frcstOneCn;
    private String frcstTwoCn;
    private String frcstThreeCn;
    private String frcstFourCn;

    // 발표일시
    private String presnatnDt;
    // 예보일시(날짜)
    private String frcstOneDt;
    private String frcstTwoDt;
    private String frcstThreeDt;
    private String frcstFourDt;

}
