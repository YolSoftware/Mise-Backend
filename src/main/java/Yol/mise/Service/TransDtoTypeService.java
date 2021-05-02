package Yol.mise.Service;

import Yol.mise.Artifact.dto.DBrealtmDTO;
import Yol.mise.Artifact.dto.OPStnMsrDTO;
import Yol.mise.Artifact.dto.TodayAirDataDTO;
import org.springframework.stereotype.Service;

@Service
public class TransDtoTypeService {
    public String getGrade(int grade) {
        switch (grade) {
            case 1:
                return "좋음";
            case 2:
                return "보통";
            case 3:
                return "나쁨";
            case 4:
                return "매우 나쁨";
        }
        return "오류";
    }

    boolean checkStringNull(String s){
        return (s == null || s.isBlank());
    }

    public TodayAirDataDTO DBrealtmDtoToTodayAirData(DBrealtmDTO db_realtm_dto) {
        TodayAirDataDTO data = new TodayAirDataDTO();
        data.setDate(db_realtm_dto.getUpdateTime());

        if (!checkStringNull(db_realtm_dto.getCoFlag())) {
            data.setCoGrade("오류");
            data.setCoValue(-1);
        } else {
            data.setCoValue(db_realtm_dto.getCo());
            data.setCoGrade(getGrade(db_realtm_dto.getCoGrade()));
        }

        if (!checkStringNull(db_realtm_dto.getNo2Flag())) {
            data.setNo2Grade("오류");
            data.setNo2Value(-1);
        } else {
            data.setNo2Value(db_realtm_dto.getNo2());
            data.setNo2Grade(getGrade(db_realtm_dto.getNo2Grade()));
        }

        if (!checkStringNull(db_realtm_dto.getO3Flag())) {
            data.setO3Grade("오류");
            data.setO3Value(-1);
        } else {
            data.setO3Value(db_realtm_dto.getO3());
            data.setO3Grade(getGrade(db_realtm_dto.getO3Grade()));
        }

        if (!checkStringNull(db_realtm_dto.getSo2Flag())) {
            data.setSo2Grade("오류");
            data.setSo2Value(-1);
        } else {
            data.setSo2Value(db_realtm_dto.getSo2());
            data.setSo2Grade(getGrade(db_realtm_dto.getSo2Grade()));
        }

        if (!checkStringNull(db_realtm_dto.getPm10Flag())) {
            data.setPm10Grade("오류");
            data.setPm10Value(-1);
        } else {
            data.setPm10Value(db_realtm_dto.getPm10());
            data.setPm10Grade(getGrade(db_realtm_dto.getPm10Grade()));
        }

        if (!checkStringNull(db_realtm_dto.getPm25Flag())) {
            data.setPm25Grade("오류");
            data.setPm25Value(-1);
        } else {
            data.setPm25Value(db_realtm_dto.getPm25());
            data.setPm25Grade(getGrade(db_realtm_dto.getPm25Grade()));
        }
        return data;
    }
}
