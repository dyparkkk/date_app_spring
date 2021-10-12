package graduateTeam.dateAppProj.domain;

import graduateTeam.dateAppProj.controller.dto.UserInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(String username, String userId, String userPwd) {
        this.username = username;
        this.userId = userId;
        this.userPwd = userPwd;
    }

    public static Member createMember(UserInfoDto dto){
        return new Member(dto.getUserName(), dto.getUserId(), dto.getUserPwd());
    }

}
