package Yol.mise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping({"/api", "/api/misenow"})
public class GetController
{
    int count = 0;

    @GetMapping("/test")
    public String ip ()
    {
        System.out.println("욜꾤쑐뾸쬴뚈뚈ㄲ뚈");
        return "욜꾤쑐뾸쬴뚈뚈ㄲ뚈";
    }

    @GetMapping("/fineDust")
    public String dustData ()
    {
        count++;
        System.out.println("미세먼지 데이터 " + count);
        return "미세먼지 데이터 민성아 빨리 보여줘" + count;
    }
}
