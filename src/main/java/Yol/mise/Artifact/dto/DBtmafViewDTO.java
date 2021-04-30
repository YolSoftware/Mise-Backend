package Yol.mise.Artifact.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter // getter 메소드
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "forecast") //table명과 클래스가 일치하면 안만들어도 됨
@Builder //builder를 사용할 수 있게 한다.
public class DBtmafViewDTO implements Serializable {

    @Id
    @Column(name = "STATION_NAME", updatable = false, nullable = false)
    public String stationName;

    @Column(name = "STATION_LOCATION",updatable = false, nullable = true)
    public String stationLocation;

    @Column(name = "GRADE_TM",updatable = false, nullable = true)
    public String gradeTm;

    @Column(name = "GRADE_AF",updatable = false, nullable = true)
    public String gradeAf;

    @Column(name = "UPDATE_TIME", updatable = false, nullable = false)
    public LocalDateTime updateTime;
}