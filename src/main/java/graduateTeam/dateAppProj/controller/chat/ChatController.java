package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.chat.dto.*;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/test") // /pub/chat/test
    public void test(HelloMessageDto dto){
        log.info("message : test !!! ");
        dto.setContent("테스트 ~~ 입장 " + dto.getName());
        messagingTemplate.convertAndSend("/sub/greetings", dto);
    }

    @MessageMapping(value = "/chat/message") //  /pub/chat/message  ??
    public void message(ChatMessageRequestDto messageDto) {
        log.info("message : " + messageDto.getMessage());
        ChatMessageResponseDto responseDto = chatService.sendMessage(messageDto);
        messagingTemplate.convertAndSend("/sub/chat/room/" + responseDto.getRoomId(), responseDto);
    }                           //    /sub/chat/room/roomId

    @PostMapping("/chat/createVote/{roomId}")
    @ResponseBody
    public String updateVote(@PathVariable String roomId,
                             @RequestBody UpdateVoteRequestDto dto,
                             @SessionAttribute(name = "user") Member loginMember) {

        chatService.updateVote(roomId, dto);
        return "success";
    }

    @PostMapping("/chat/vote/{roomId}")
    @ResponseBody
    public String participateVote(@PathVariable String roomId,
                                @SessionAttribute(name = "user") Member loginMember) {
        chatService.addVoteMember(roomId, loginMember);
        return "success";
    }

    @GetMapping("/chat/vote/{roomId}")
    @ResponseBody
    public VoteInfoResponseDto voteInfo(@PathVariable String roomId) {
        // 유저 정보
        return chatService.voteInfo(roomId);
    }

    @PostMapping("/chat/endVote/{roomId}")
    @ResponseBody
    public String endVote(@PathVariable String roomId) {
        chatService.endVote(roomId);
        return "success";
    }

}
