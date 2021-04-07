package Yol.mise.artifact;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Yol.mise.artifact.dao.UserDAO;
import Yol.mise.artifact.dto.UserDTO;

import java.util.List;

@RestController
@MapperScan(basePackages = "Yol.mise.artifact.dao")
public class UserController{
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/users")
    public List<UserDTO> users(@RequestParam(value="number_station",defaultValue="")int number_station) throws Exception{
        final UserDTO param = new UserDTO(number_station,null);
        final List<UserDTO> userList = userDAO.selectUsers(param);
        return userList;
    }
}