package Yol.mise.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController
{
    @RequestMapping("/")
    public String hello()
    {
        System.out.println("hello");
        return "현석이 안녕";
    }
}
