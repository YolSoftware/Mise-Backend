package Yol.mise.Service;

import Yol.mise.Artifact.dao.DBForecastViewDAO;
import Yol.mise.Artifact.dto.DBForecastViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DBForecastViewService {
    @Autowired
    private DBForecastViewDAO db_forecast_view_dao;

    public Optional<DBForecastViewDTO> findForecast(String name) {
        return db_forecast_view_dao.findById(name);
    }

    public List<DBForecastViewDTO> findAll(){
        return db_forecast_view_dao.findAll();
    }


}
