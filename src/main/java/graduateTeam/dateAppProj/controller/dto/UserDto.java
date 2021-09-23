package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter @RequiredArgsConstructor
public class UserDto {

    private final Long UserId;
    private final List<Member> userList;
}
