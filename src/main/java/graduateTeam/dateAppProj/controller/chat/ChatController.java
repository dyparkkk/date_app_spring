package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.chat.dto.ChatMessageRequestDto;
import graduateTeam.dateAppProj.controller.chat.dto.ChatMessageResponseDto;
import graduateTeam.dateAppProj.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/message") //  /pub/chat/message
    public void message(ChatMessageRequestDto messageDto) {
        log.info("message : " + messageDto.getMessage());
        ChatMessageResponseDto responseDto = chatService.sendMessage(messageDto);
        messagingTemplate.convertAndSend("/sub/chat/room/"+ responseDto.getRoomId(), responseDto);
    }                           //    /sub/chat/room/roomId
}
