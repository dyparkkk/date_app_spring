package graduateTeam.dateAppProj.controller;

import graduateTeam.dateAppProj.controller.dto.LoginInfoDto;
import graduateTeam.dateAppProj.controller.dto.UserInfoDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequiredArgsConstructor

@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public Long signupMember(@RequestBody UserInfoDto dto) throws Exception {
        return memberService.save(dto);
    }

    @GetMapping("/users")
    public List<Member> showMembers() {
        return memberService.findAll();
    }

    @PostMapping("/login")
    public Long login(@RequestBody LoginInfoDto dto, HttpServletRequest req) {
        return memberService.login(dto, req);
    }

}
