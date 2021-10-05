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
    private String message;

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.roomId = chatRoom.getId().toString();
        this.name = chatRoom.getName();
        this.message = name + "방에 입장하셨습니다. ";
    }

}
