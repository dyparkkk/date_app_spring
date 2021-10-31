package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.domain.VoteHistory;
import graduateTeam.dateAppProj.domain.chat.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class VoteHistoryAllDto {
    private String name;
    private Category category;
    private String userId;

    @Builder
    public VoteHistoryAllDto(String name, Category category, String userId) {
        this.name = name;
        this.category = category;
        this.userId = userId;
    }

    public VoteHistoryAllDto(VoteHistory voteHistory){
        name = voteHistory.getName();
        category = voteHistory.getCategory();
        userId = voteHistory.getMember().getUserId();
    }

    public VoteHistoryAllDto toDto(VoteHistory voteHistory) {
        return VoteHistoryAllDto.builder()
                .category(voteHistory.getCategory())
                .name(voteHistory.getName())
                .userId(voteHistory.getMember().getUserId())
                .build();
    }
}
