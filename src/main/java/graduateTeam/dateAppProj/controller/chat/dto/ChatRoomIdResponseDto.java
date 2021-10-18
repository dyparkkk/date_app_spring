package graduateTeam.dateAppProj.controller.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomIdResponseDto {
    private String roomId;

    public ChatRoomIdResponseDto(String roomId) {
        this.roomId = roomId;
    }
}
