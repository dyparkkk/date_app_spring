package graduateTeam.dateAppProj.controller;

import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/users/new")
    public String newMember(@RequestParam(defaultValue = "user1") String username,
                            @RequestParam String userId) {
        Member member = new Member();
        member.setUsername(username);
        member.setUserId(userId);
        memberService.save(member);

        return "ok";
    }

    @GetMapping("/users")
    public List<Member> showMembers() {
        return memberService.findAll();
    }

    @GetMapping("/makeMember")
    public String makeMember() {
        Member member = new Member();
        member.setUsername("userName1");
        member.setUserId("userId");
        member.setUserPwd("pwd");
        memberService.save(member);

        Member member1 = new Member();
        member1.setUsername("userName2");
        member1.setUserId("userId2");
        member1.setUserPwd("pwd2");
        memberService.save(member1);

        return "ok";
    }
}
