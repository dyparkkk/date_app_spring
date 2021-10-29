package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.domain.Vote;
import graduateTeam.dateAppProj.domain.VoteState;
import graduateTeam.dateAppProj.domain.chat.Category;
import graduateTeam.dateAppProj.domain.chat.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class RequestCreateChatRoomDto {
    private String name; // 방제목
    private String  userId; // login_ID
    private double latitude;
    private double longitude;
    private Category category;

    public ChatRoom toEntity(){
        Vote vote = Vote.builder()
                .state(VoteState.BEFORE)
                .voteName("null")
                .build();

        return ChatRoom.builder()
                .ownerId(userId)
                .category(category)
                .latitude(latitude)
                .longitude(longitude)
                .name(name)
                .vote(vote)
                .build();
    }

}
