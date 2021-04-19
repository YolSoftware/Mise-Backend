package Yol.mise.Artifact.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter // getter 메소드
@ToString
@AllArgsConstructor
@Entity(name = "station_air_realtm") //table명과 클래스가 일치하면 안만들어도 됨
@Builder //builder를 사용할 수 있게 한다.
public class DBrealtmDTO implements Serializable {
    public DBrealtmDTO() {
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)//mysql의 auto_increment를 그대로 사용
    @Column(updatable = true)
    public Long station_number;

    @Id
    @Column(updatable = true, nullable = false)
    public String station_name;

    @Column(updatable = true, nullable = false)
    public int all_air_grade;

    @Column(updatable = true, nullable = false)
    public float so2;

    @Column(updatable = true, nullable = true)
    public float co;

    @Column(updatable = true, nullable = true)
    public float o3;

    @Column(updatable = true, nullable = true)
    public float no2;

    @Column(updatable = true, nullable = true)
    public int pm10;

    @Column(updatable = true, nullable = true)
    public int pm25;

    @Column(updatable = true, nullable = true)
    public int so2_grade;

    @Column(updatable = true, nullable = true)
    public int co_grade;

    @Column(updatable = true, nullable = true)
    public int o3_grade;

    @Column(updatable = true, nullable = true)
    public int no2_grade;

    @Column(updatable = true, nullable = true)
    public int pm10_grade;

    @Column(updatable = true, nullable = true)
    public int pm25_grade;

    @Column(updatable = true, nullable = true)
    public int all_air;

}
