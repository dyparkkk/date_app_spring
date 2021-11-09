package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewUserScoreResponseDto {
    private String userId;
    private String userName;
    private double Score;

    public ViewUserScoreResponseDto(Member member){
        this.userId = member.getUserId();
        this.userName = member.getUsername();
        this.Score = member.getScore().doubleValue();
    }
}
