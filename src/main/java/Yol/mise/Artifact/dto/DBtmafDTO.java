package Yol.mise.Artifact.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter // getter 메소드
@ToString
@AllArgsConstructor
//@NoArgsConstructor
@Entity(name = "station_air_tm_af") //table명과 클래스가 일치하면 안만들어도 됨
@Builder //builder를 사용할 수 있게 한다.
public class DBtmafDTO implements Serializable {
    public DBtmafDTO(){
    }
    @Id
    @Column(name = "station_location",updatable = true, nullable = false)
    public String stationLocation;

    @Column(name = "grade_tm",updatable = true, nullable = true)
    public int gradeTm;

    @Column(name = "grade_af",updatable = true, nullable = true)
    public int gradeAf;

    @Column(name = "update_time", updatable = true, nullable = true)
    public LocalDateTime updateTime;
}