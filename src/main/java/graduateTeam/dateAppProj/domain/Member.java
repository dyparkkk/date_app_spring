package graduateTeam.dateAppProj.domain;

import graduateTeam.dateAppProj.controller.dto.UserInfoDto;
import graduateTeam.dateAppProj.domain.history.HistoryMember;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @NoArgsConstructor
@Slf4j
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

    @Column(nullable = false)
    private BigDecimal score;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<HistoryMember> historyMembers = new ArrayList<>();



    public Member(String username, String userId, String userPwd) {
        this.username = username;
        this.userId = userId;
        this.userPwd = userPwd;
        this.score = new BigDecimal("3.00");
    }

    public static Member createMember(UserInfoDto dto){
        return new Member(dto.getUserName(), dto.getUserId(), dto.getUserPwd());
    }

    //== 연관관계 메서드 ==//
    public void addHistoryMember(HistoryMember historyMember) {
        historyMembers.add(historyMember);
    }

    //== 비즈니스 로직 ==//
    public void setScore(BigDecimal score){
        this.score = score;
    }
}
