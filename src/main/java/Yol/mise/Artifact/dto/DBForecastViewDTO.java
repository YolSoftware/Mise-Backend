package Yol.mise.Artifact.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "forecast")
public class DBForecastViewDTO {
    @Id
    private String STATION_NAME;

    private String STATION_LOCATION;
    private int GRADE_AF;
    private int GRADE_TM;
    private String UPDATE_TIME;

}
