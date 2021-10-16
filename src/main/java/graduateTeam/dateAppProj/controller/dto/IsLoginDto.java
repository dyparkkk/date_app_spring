package graduateTeam.dateAppProj.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class IsLoginDto {
    private String message;
    private String userId;
    private String userName;

    @Builder
    public IsLoginDto(String message, String userId, String userName) {
        this.message = message;
        this.userId = userId;
        this.userName = userName;
    }
}
