package Yol.mise.Controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Yol.mise.Artifact.dao.DBStNmDAO;
import Yol.mise.Artifact.dto.DBStNmDTO;

import java.util.List;

@RestController
@MapperScan(basePackages = "Yol.mise.artifact.dao")
public class UserController{
    @Autowired
    private DBStNmDAO DBStnNm;

    @RequestMapping("/users")
    public List<DBStNmDTO> users(@RequestParam String ns) throws Exception{
        final DBStNmDTO param = new DBStNmDTO(ns,null,null,null);
        final List<DBStNmDTO> userList = DBStnNm.selectUsers(param);
        return userList;
    }

    @GetMapping(value = "/table/{name}")
    public List<DBStNmDTO> findOne(@PathVariable String name)throws Exception{
        final DBStNmDTO param = new DBStNmDTO(name,null,null,null);
        final List<DBStNmDTO> tableList = DBStnNm.selectUsers(param);

        return tableList;
    }

}