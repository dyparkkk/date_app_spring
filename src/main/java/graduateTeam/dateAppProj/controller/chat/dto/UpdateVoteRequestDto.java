package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.domain.VoteState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UpdateVoteRequestDto {
    private String name;
    private VoteState state;
}
