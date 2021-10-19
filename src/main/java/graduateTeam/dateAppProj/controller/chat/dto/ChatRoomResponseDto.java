package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.domain.Category;
import graduateTeam.dateAppProj.domain.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatRoomResponseDto {
    private String roomId;
    private String name;
    private double latitude;
    private double longitude;
    private Category category;
    private int userNumber;


    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.roomId = chatRoom.getId().toString();
        this.name = chatRoom.getName();
        this.latitude = chatRoom.getLatitude();
        this.longitude = chatRoom.getLongitude();
        this.category = chatRoom.getCategory();
        this.userNumber = chatRoom.getUserNumber();
    }

}
