package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserInfoDto {
    private String userName;
    private String userId;
    private String userPwd;
    private Role role;

    public UserInfoDto(String userName, String userId, String userPwd) {
        this.userName = userName;
        this.userId = userId;
        this.userPwd = userPwd;
        this.role = Role.ROLE_USER;
    }
}
