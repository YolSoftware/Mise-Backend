package Yol.mise.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class PostController
{
    @PostMapping("/ip")
    public ResponseEntity<String> ip (HttpServletRequest request) {
        // 요청을 보낸 클라이언트의 IP주소를 반환합니다.
        System.out.println(request.getRemoteAddr());
        return ResponseEntity.ok(request.getRemoteAddr());
    }

    @PostMapping("/test")
    public String ip ()
    {
        System.out.println("욜꾤쑐뾸쬴뚈뚈ㄲ뚈");
        return "욜꾤쑐뾸쬴뚈뚈ㄲ뚈";
    }
}
