package Yol.mise.Artifact.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter // getter 메소드
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "station_air_realtm") //table명과 클래스가 일치하면 안만들어도 됨
@Builder //builder를 사용할 수 있게 한다.
public class DBrealtmDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//mysql의 auto_increment를 그대로 사용
    @Column(name ="station_number",updatable = true)
    public Long stationNumber;

    @Column(name ="station_name",updatable = true, nullable = false)
    public String stationName;

    @Column(name ="all_air_grade",updatable = true, nullable = false)
    public int allAirGrade;

    @Column(name ="so2",updatable = true, nullable = false)
    public float so2;

    @Column(name ="co",updatable = true, nullable = true)
    public float co;

    @Column(name ="o3",updatable = true, nullable = true)
    public float o3;

    @Column(name ="no2",updatable = true, nullable = true)
    public float no2;

    @Column(name ="pm10",updatable = true, nullable = true)
    public int pm10;

    @Column(name ="pm25",updatable = true, nullable = true)
    public int pm25;

    @Column(name ="so2_grade",updatable = true, nullable = true)
    public int so2Grade;

    @Column(name ="co_grade",updatable = true, nullable = true)
    public int coGrade;

    @Column(name ="o3_grade",updatable = true, nullable = true)
    public int o3Grade;

    @Column(name ="no2_grade",updatable = true, nullable = true)
    public int no2Grade;

    @Column(name ="pm10_grade",updatable = true, nullable = true)
    public int pm10Grade;

    @Column(name ="pm25_grade",updatable = true, nullable = true)
    public int pm25Grade;

    @Column(name ="so2Flag",updatable = true, nullable = true)
    public String so2Flag;

    @Column(name ="coFlag",updatable = true, nullable = true)
    public String coFlag;

    @Column(name ="o3Flag",updatable = true, nullable = true)
    public String o3Flag;

    @Column(name ="no2Flag",updatable = true, nullable = true)
    public String no2Flag;

    @Column(name ="pm10Flag",updatable = true, nullable = true)
    public String pm10Flag;

    @Column(name ="pm25Flag",updatable = true, nullable = true)
    public String pm25Flag;

    @Column(name ="all_air",updatable = true, nullable = true)
    public int allAir;

    @Column(name = "update_time", updatable = true, nullable = true)
    public LocalDateTime updateTime;

}
