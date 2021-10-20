package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserListInfoDto {
    private String userId;
    private String name;

    public UserListInfoDto(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public UserListInfoDto createDto(Member member) {
        return new UserListInfoDto(member.getUserId(), member.getUsername());
    }
}
