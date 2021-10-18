package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class RequestCreateChatRoomDto {
    private String name; // 방제목
    private String  userId; // login_ID
    private double latitude;
    private double longitude;
    private Category category;

    @Override
    public String toString() {
        return "RequestCreateChatRoomDto{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", category=" + category +
                '}';
    }
}
