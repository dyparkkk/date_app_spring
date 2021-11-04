package graduateTeam.dateAppProj.controller;

import graduateTeam.dateAppProj.controller.dto.IsLoginOrUserInfoDto;
import graduateTeam.dateAppProj.controller.dto.LoginRequestDto;
import graduateTeam.dateAppProj.controller.dto.UserInfoDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.service.ChatRoomService;
import graduateTeam.dateAppProj.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final ChatRoomService chatRoomService;

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
    public UserInfoDto login(@RequestBody LoginRequestDto dto, HttpServletRequest req) {
        return memberService.login(dto, req);
    }

    // login 에 룸 아이디 추가

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
            List<String> chatRoomList = chatRoomService.FindChatRoomByMember(loginMember);
            if(chatRoomList.isEmpty() == false) {
                dto.setRoomId(chatRoomList.get(0));
            }
        }
        dto.setMessage(message);
        return dto;
    }

    @GetMapping("/room")
    @ResponseBody
    public Map<String, String> findMembersChatRoom(@SessionAttribute(name = "user", required = false) Member loginMember) {
        Map<String, String> response = new HashMap<>();
        if(loginMember == null){
            throw new IllegalStateException("세션 확인 필요");
        }
        List<String> chatRoomList = chatRoomService.FindChatRoomByMember(loginMember);
        if(chatRoomList.isEmpty() == false) {
            response.put("roomId", chatRoomList.get(0));
        }

        return response;
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpSession session,
                         @SessionAttribute(name = "user", required = false) Member loginMember) {
        session.invalidate();
        if(loginMember == null) return "not login";
        return "logout success";
    }
}
