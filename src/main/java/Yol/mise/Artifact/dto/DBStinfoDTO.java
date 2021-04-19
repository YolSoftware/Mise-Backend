package Yol.mise.Artifact.dto;
import javax.persistence.*;
import java.io.Serializable;

import lombok.*;

@Getter // getter 메소드
@ToString
@AllArgsConstructor
@Entity(name = "station_info") //table명과 클래스가 일치하면 안만들어도 됨
@Builder //builder를 사용할 수 있게 한다.
public class DBStinfoDTO implements Serializable {
    public DBStinfoDTO() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//mysql의 auto_increment를 그대로 사용
    @Column(updatable = true, nullable = false)
    public Long station_number;

    @Column(updatable = true, nullable = false)
    public String station_name;

    @Column(updatable = true, nullable = false)
    public String station_location;

    @Column(updatable = true, nullable = true)
    public float station_x;

    @Column(updatable = true, nullable = true)
    public float station_y;
}