package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatRoomResponseDto {
    private String roomId;
    private String name;

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.roomId = chatRoom.getId().toString();
        this.name = chatRoom.getName();
    }

}
