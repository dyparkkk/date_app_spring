package graduateTeam.dateAppProj.controller.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatMessageResponseDto {
    private String roomId;
    private String senderId;
    private String message;
    private String senderName;
    private LocalDateTime time;

    @Builder
    public ChatMessageResponseDto(String roomId, String senderId, String message, String senderName) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.message = message;
        this.senderName = senderName;
        time = LocalDateTime.now();
    }
}
