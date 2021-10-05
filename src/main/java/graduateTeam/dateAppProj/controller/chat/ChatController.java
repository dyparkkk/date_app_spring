package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.dto.ChatMessageDto;
import graduateTeam.dateAppProj.domain.ChatMessage;
import graduateTeam.dateAppProj.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/message") //  /pub/chat/message
    public void message(ChatMessageDto messageDto) {
        chatService.sendMessage(messageDto);
        messagingTemplate.convertAndSend("/sub/chat/room/"+ messageDto.getRoomId(), messageDto);
    }                           //    /sub/chat/room/roomId
}
