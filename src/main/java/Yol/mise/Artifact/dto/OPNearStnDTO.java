package Yol.mise.Artifact.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OPNearStnDTO {
    private String tm;  // 요청한 TM 좌표와 측정소 간의 거리 (단위 : KM)
    private String stationName; // 측정소 이름 (ex - 창전동)
    private String addr;    // 측정소가 위치한 주소 (ex - 경기도 이천시 부발읍 무촌로 117부발보건지소 옥상)
}

