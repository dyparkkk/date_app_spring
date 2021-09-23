package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.ChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
public class ChatMessageDto {

    private UUID roomId;
    private String sender;
    private String message;
    private LocalDateTime time;

    @Builder
    public ChatMessageDto(UUID roomId, String sender, String message, LocalDateTime time) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.time = time;
    }

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .roomId(roomId)
                .sender(sender)
                .message(message)
                .time(time)
                .build();
    }
}
