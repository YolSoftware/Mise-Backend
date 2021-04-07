package Yol.mise.artifact.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor // 자동으로 모든 매개변수를 받는 생성자를 생성
@Getter // Getter 생성
@Setter // Setter 생성
public class UserDTO {
    private int number_station;
    private String station;
    //private int seq;
    //private String name;
    //private String country;
}
