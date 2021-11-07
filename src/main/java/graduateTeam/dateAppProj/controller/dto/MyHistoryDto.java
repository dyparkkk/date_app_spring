package graduateTeam.dateAppProj.controller.dto;

import graduateTeam.dateAppProj.domain.chat.Category;
import graduateTeam.dateAppProj.domain.history.History;
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

    public MyHistoryDto(History history) {
        historyId = history.getId();
        chatRoomName = history.getChatRoomName();
        voteName = history.getVoteName();
        category = history.getCategory();
    }
}
