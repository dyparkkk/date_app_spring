package graduateTeam.dateAppProj.controller;

import graduateTeam.dateAppProj.controller.dto.IsLoginOrUserInfoDto;
import graduateTeam.dateAppProj.controller.dto.LoginInfoDto;
import graduateTeam.dateAppProj.controller.dto.UserInfoDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.service.ChatService;
import graduateTeam.dateAppProj.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final ChatService chatService;

    @PostMapping("/signup")
    @ResponseBody
    public Long signupMember(@RequestBody UserInfoDto dto) throws Exception {
        return memberService.save(dto);
    }

    @GetMapping("/users")
    @ResponseBody
    public List<Member> showMembers() {
        return memberService.findAll();
    }

    @PostMapping("/login")
    @ResponseBody
    public UserInfoDto login(@RequestBody LoginInfoDto dto, HttpServletRequest req) {
        return memberService.login(dto, req);
    }

//    @GetMapping("/hi")
//    public String hi(@SessionAttribute(name = "user", required = false) Member loginMember,
//                     Model model){
//        if(loginMember != null){
//            model.addAttribute("userId", loginMember.getUserId());
//        } else{
//            model.addAttribute("userId", "guest");
//        }
//
//
//        return "index";
//    }

    @GetMapping("/hi")
    @ResponseBody
    public IsLoginOrUserInfoDto isLoginAndUserInfo(@SessionAttribute(name = "user", required = false) Member loginMember) {
        String message;
        IsLoginOrUserInfoDto dto = new IsLoginOrUserInfoDto();
        if(loginMember == null) message = "error : not login";
        else {
            message = "login success";
            dto.setUserId(loginMember.getUserId());
            dto.setUserName(loginMember.getUsername());
            List<String> chatRoomList = chatService.FindChatRoomByMember(loginMember.getUserId());
            if(chatRoomList.isEmpty() == false) {
                dto.setRoomId(chatRoomList.get(0));
            }
        }
        dto.setMessage(message);
        return dto;
    }
}
