package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.dto.ChatMessageDto;
import graduateTeam.dateAppProj.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message") //  /pub/chat/message
    public void message(ChatMessageDto messageDto) {
        messagingTemplate.convertAndSend("/sub/chat/room/"+ messageDto.getRoomId(), messageDto);
    }                           //    /sub/chat/room/roomId
}
