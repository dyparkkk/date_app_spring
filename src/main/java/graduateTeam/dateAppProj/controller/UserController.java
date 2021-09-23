package graduateTeam.dateAppProj.controller;

import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.service.user.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UsersService usersService;

    @GetMapping("/users/new")
    public String newMember(@RequestParam(defaultValue = "user1") String username){
        Member member = new Member();
        member.setUsername(username);
        usersService.save(member);

        return "ok";
    }

    @GetMapping("/users")
    public List<Member> showMembers() {
        return usersService.findAll();
    }
}
