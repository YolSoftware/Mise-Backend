package Yol.mise.Artifact.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AirDataDTO {
    private OPStnMsrDTO now;
    private OPWeekFrcstDTO future;
}
