package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.ChatMessage;
import graduateTeam.dateAppProj.domain.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
public class ChatMessageDto {

    private String roomId;
    private String senderId;
    private String message;
    private LocalDateTime time;

    @Builder
    public ChatMessageDto(String roomId, String senderId, String message) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.message = message;
    }

//    public ChatMessage toEntity(ChatRoom chatRoom) {
//        return ChatMessage.builder()
//                .chatRoom(chatRoom)
//                .senderId(sender)
//                .message(message)
//                .build();
//    }
}
