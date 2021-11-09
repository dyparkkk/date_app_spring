package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HistoryUserListDto {
    private String userId;
    private String userName;
    private double score;

    private List<HistoryUserListDto> mainList;

    public HistoryUserListDto(Member member) {
        userId = member.getUserId();
        userName = member.getUsername();
    }
}
