package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.chat.dto.*;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.VoteHistory;
import graduateTeam.dateAppProj.repository.ChatRepository;
import graduateTeam.dateAppProj.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/test")
    public void test(ChatMessageRequestDto dto){
        log.info("message : test !!! ");
        dto.setMessage("테스트 ~~ 입장 ");
        messagingTemplate.convertAndSend("/sub", dto);
    }

    @MessageMapping("/message") //  /pub/chat/message
    public void message(ChatMessageRequestDto messageDto) {
        log.info("message : " + messageDto.getMessage());
        ChatMessageResponseDto responseDto = chatService.sendMessage(messageDto);
        messagingTemplate.convertAndSend("/sub/chat/room/" + responseDto.getRoomId(), responseDto);
    }                           //    /sub/chat/room/roomId

    @PostMapping("/createVote/{roomId}")
    public String updateVote(@PathVariable String roomId,
                             @RequestBody UpdateVoteRequestDto dto,
                             @SessionAttribute(name = "user") Member loginMember) {

        chatService.updateVote(roomId, dto);
        return "success";
    }

    @PostMapping("/vote/{roomId}")
    public String participateVote(@PathVariable String roomId,
                                @SessionAttribute(name = "user") Member loginMember) {
        chatService.addVoteMember(roomId, loginMember);
        return "success";
    }

    @GetMapping("/vote/{roomId}")
    public VoteInfoResponseDto voteInfo(@PathVariable String roomId) {
        // 유저 정보
        return chatService.voteInfo(roomId);
    }

    @PostMapping("/endVote/{roomId}")
    public String endVote(@PathVariable String roomId) {
        chatService.endVote(roomId);
        return "success";
    }

}
