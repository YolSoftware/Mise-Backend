package Yol.mise.Artifact.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//측정소 이름을 저장하는 DTO
@AllArgsConstructor // 자동으로 모든 매개변수를 받는 생성자를 생성
@Getter // Getter 생성
@Setter // Setter 생성
public class DBStNmDTO {
    private String station_name;
    private String station_location;
    private String station_x;
    private String station_y;
    //private int seq;
    //private String name;
    //private String country;
}
