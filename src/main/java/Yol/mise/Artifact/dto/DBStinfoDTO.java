package Yol.mise.Artifact.dto;
import javax.persistence.*;
import java.io.Serializable;

import lombok.*;

@Getter // getter 메소드
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "station_info") //table명과 클래스가 일치하면 안만들어도 됨
@Builder //builder를 사용할 수 있게 한다.
public class DBStinfoDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//mysql의 auto_increment를 그대로 사용
    @Column(name = "station_number",updatable = true, nullable = false)
    public Long stationNumber;

    @Column(name = "station_name",updatable = true, nullable = false)
    public String stationName;

    @Column(name = "station_location",updatable = true, nullable = false)
    public String stationLocation;

    @Column(name = "station_x",updatable = true, nullable = true)
    public float stationX;

    @Column(name = "station_y",updatable = true, nullable = true)
    public float stationY;
}