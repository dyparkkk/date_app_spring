package graduateTeam.dateAppProj.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class IsLoginOrUserInfoDto {
    private String message;
    private String userId;
    private String userName;
    private String roomId;

    @Builder
    public IsLoginOrUserInfoDto(String message, String userId, String userName, String roomId) {
        this.message = message;
        this.userId = userId;
        this.userName = userName;
        this.roomId = roomId;
    }
}
