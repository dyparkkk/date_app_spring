package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.domain.chat.MessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ChatMessageRequestDto {

    private String roomId;
    private String senderId;
    private String message;
    private MessageType type;

    @Builder
    public ChatMessageRequestDto(String roomId, String senderId, String message, MessageType type) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.message = message;
        this.type = type;
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
                .type(type)
                .build();
    }
}
