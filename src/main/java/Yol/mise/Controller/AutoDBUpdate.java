package Yol.mise.Controller;

import Yol.mise.Artifact.dto.OPStnMsrDTO;
import Yol.mise.Artifact.dto.OPWeekFrcstDTO;
import Yol.mise.Service.DBStinfoService;
import Yol.mise.Service.DBrealtmService;
import Yol.mise.Service.DBtmafService;
import Yol.mise.Service.GetAirDataService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableScheduling
@Configuration
public class AutoDBUpdate {
    private Gson gson = new Gson();
    private final List<String> city_name = List.of("서울", "인천", "경기북부","경기남부","강원영서", "강원영동", "대전", "세종", "충남", "충북", "광주","전북", "전남", "부산","대구", "울산", "경북", "경남", "제주");
    @Autowired
    private DBtmafService db_tmaf_service;

    @Autowired
    private DBStinfoService db_stninfo_service;

    @Autowired
    private DBrealtmService db_realtm_service;

    @Autowired
    private GetAirDataService get_air_data_service;

    @Scheduled(cron = "0 1 0 * * *")
    public OPWeekFrcstDTO AutoUpdateWeekMsr() throws Exception {
        LocalDateTime update_time = LocalDateTime.now();
        update_time = update_time.minusDays(2);
        System.out.println(update_time);
        GetAirDataService getAirDataService = new GetAirDataService();
        List<OPWeekFrcstDTO> opWeekFrcstDTOS = getAirDataService.callWeekMsrApi(update_time.format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));
        System.out.println(opWeekFrcstDTOS.get(0).getFrcstOneCn());

        Map<String, String> msr_tm = new HashMap<>();
        Map<String, String> msr_af = new HashMap<>();
        msr_tm = gson.fromJson("{" + opWeekFrcstDTOS.get(0).getFrcstOneCn()+"}", msr_tm.getClass());
        msr_af = gson.fromJson("{" + opWeekFrcstDTOS.get(0).getFrcstTwoCn()+"}", msr_af.getClass());

        for(String city : city_name) {
            db_tmaf_service.UpdateTmAfLocation(city, msr_tm.get(city), msr_af.get(city), update_time);
        }
        return opWeekFrcstDTOS.get(0);
    }

    @Scheduled(cron = "0 3 0/2 * * *")
    public boolean AutoUpdateRltmMsr() throws Exception {
        int cnt = 0;
        List<Object> stn_names = db_stninfo_service.findAllStationName();
        List<OPStnMsrDTO> stn_msr_dtos;
        for(Object stn_name : stn_names) {
            cnt += 1;
            if (cnt >= 10) break;
            stn_msr_dtos = get_air_data_service.callStnMsrApi(stn_name.toString());
            // 다 잘 됬다고 가정합시다.
            if (stn_msr_dtos != null) {
                db_realtm_service.updateStationAir(stn_name.toString(), stn_msr_dtos.get(0));
            }
        }
        return true;
    }

}

