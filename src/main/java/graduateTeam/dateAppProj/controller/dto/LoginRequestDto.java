package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LoginRequestDto {
    private String userId;
    private String userPwd;

    public LoginRequestDto(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }
}
