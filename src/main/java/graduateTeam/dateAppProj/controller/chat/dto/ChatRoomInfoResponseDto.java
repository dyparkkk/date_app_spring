package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.controller.dto.UserListInfoDto;
import graduateTeam.dateAppProj.domain.Vote;
import graduateTeam.dateAppProj.domain.chat.Category;
import graduateTeam.dateAppProj.domain.chat.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ChatRoomInfoResponseDto {
    private List<UserListInfoDto> userList;
    private String name;
    private double latitude;
    private double longitude;
    private Category category;
    private String ownerId;
    private int userNumber;
    private Vote vote;

    public ChatRoomInfoResponseDto(List<UserListInfoDto> userList,
                                   String name, double latitude,
                                   double longitude, Category category,
                                   String ownerId, int userNumber,
                                   Vote vote) {
        this.userList = userList;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.ownerId = ownerId;
        this.userNumber = userNumber;
        this.vote = vote;
    }


    public ChatRoomInfoResponseDto createDto(ChatRoom chatRoom, List<UserListInfoDto> userList) {
        return new ChatRoomInfoResponseDto(userList, chatRoom.getName(), chatRoom.getLatitude(),
                chatRoom.getLongitude(), chatRoom.getCategory(), chatRoom.getOwnerId(),
                chatRoom.getUserNumber(), chatRoom.getVote());
    }
}
