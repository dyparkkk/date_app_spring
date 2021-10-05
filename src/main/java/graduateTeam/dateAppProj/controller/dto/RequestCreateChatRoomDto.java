package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class RequestCreateChatRoomDto {
    private String name;
    private String  userId;
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
