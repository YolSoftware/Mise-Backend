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
    private HashMap<String, String> tomorrow;
    private HashMap<String, String> dayAfterTommorow;

//    public void setToday(DBrealtmDTO today2)
//    {
//
//    }
}
