package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.Member;
import lombok.Data;

@Data
public class HistoryUserListDto {
    private String userId;
    private String userName;
    private double score;

    public HistoryUserListDto(Member member) {
        userId = member.getUserId();
        userName = member.getUsername();
    }
}
