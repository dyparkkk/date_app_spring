package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.chat.Category;
import graduateTeam.dateAppProj.domain.history.History;
import graduateTeam.dateAppProj.domain.history.HistoryMember;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MyHistoryDto {
    private Long historyId;

    private String chatRoomName;

    private String voteName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private double score;

    public MyHistoryDto(History history, HistoryMember hm) {
        historyId = history.getId();
        chatRoomName = history.getChatRoomName();
        voteName = history.getVoteName();
        category = history.getCategory();
        score = hm.getScore().doubleValue();
    }
}
