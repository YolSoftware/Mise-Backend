package Yol.mise.Artifact.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class PostAirDataDTO {
    private String stationAddress;
    //private OPStnMsrDTO today;
    private TodayAirDataDTO today;
    private TmAirDataDTO tomorrow;
    private AfAirDataDTO dayAfterTomorrow;
}
