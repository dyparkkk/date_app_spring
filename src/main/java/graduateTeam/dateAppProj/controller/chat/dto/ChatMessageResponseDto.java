package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.domain.chat.MessageType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ChatMessageResponseDto {
    private String roomId;
    private String senderId;
    private String message;
    private String senderName;
    private LocalDateTime time;
    private MessageType type;

    @Builder
    public ChatMessageResponseDto(String roomId, String senderId, String message,
                                  String senderName, MessageType type) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.message = message;
        this.senderName = senderName;
        time = LocalDateTime.now();
        this.type = type;
    }


}
