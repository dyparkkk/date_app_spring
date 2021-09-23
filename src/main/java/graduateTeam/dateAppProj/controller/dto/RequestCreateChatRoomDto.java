package graduateTeam.dateAppProj.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestCreateChatRoomDto {
    private String name;
    private Long creatorId;
}
