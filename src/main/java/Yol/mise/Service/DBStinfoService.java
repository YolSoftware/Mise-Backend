package Yol.mise.Service;

import Yol.mise.Artifact.dao.DBStinfoDAO;
import Yol.mise.Artifact.dto.DBStinfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DBStinfoService {
    @Autowired
    private DBStinfoDAO dbStinfoDAO; //repository
/*
    @Transactional //save할때
    public void saveStinfo(String station_name){
        Optional<DBStinfoDTO> ?? dbStinfoDAO.findById(station_name);
        if(?? == 원하는_정보){//isEmpty()
            dbStinfoDAO.save(??);
        }
    }

    public D
*/
}
