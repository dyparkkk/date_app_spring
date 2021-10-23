package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.controller.dto.UserListInfoDto;
import graduateTeam.dateAppProj.domain.chat.Category;
import graduateTeam.dateAppProj.domain.chat.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatRoomInfoResponseDto {
    private List<UserListInfoDto> userList;
    private String name;
    private double latitude;
    private double longitude;
    private Category category;
    private String ownerId;
    private int userNumber;

    public ChatRoomInfoResponseDto(List<UserListInfoDto> userList, String name,
                                   double latitude, double longitude,
                                   Category category, int userNumber, String ownerId) {
        this.userList = userList;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.userNumber = userNumber;
        this.ownerId = ownerId;
    }

    public ChatRoomInfoResponseDto createDto(ChatRoom chatRoom, List<UserListInfoDto> userList) {
        return new ChatRoomInfoResponseDto(userList, chatRoom.getName(), chatRoom.getLatitude(),
                chatRoom.getLongitude(), chatRoom.getCategory(), chatRoom.getUserNumber(),
                chatRoom.getOwnerId());
    }
}
