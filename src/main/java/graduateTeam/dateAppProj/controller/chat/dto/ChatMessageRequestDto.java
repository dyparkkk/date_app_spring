package graduateTeam.dateAppProj.controller.chat.dto;

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
public class ChatMessageRequestDto {

    private String roomId;
    private String senderId;
    private String message;

    @Builder
    public ChatMessageRequestDto(String roomId, String senderId, String message) {
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

    public ChatMessageResponseDto toResponseDto(String name){
        return ChatMessageResponseDto.builder()
                .senderName(name)
                .message(message)
                .senderId(senderId)
                .roomId(roomId)
                .build();
    }
}
