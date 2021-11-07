package graduateTeam.dateAppProj.domain.history;

import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.chat.Category;
import graduateTeam.dateAppProj.domain.chat.ChatRoom;
import graduateTeam.dateAppProj.domain.chat.MemberChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue
    @Column(name = "history_id")
    private Long id;

    private String chatRoomName;

    private String voteName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "history")
    private List<HistoryMember> historyMembers = new ArrayList<>();

    @Builder
    public History(String chatRoomName,
                   Category category, String voteName) {
        this.chatRoomName = chatRoomName;
        this.voteName = voteName;
        this.category = category;
    }

    public History(ChatRoom chatRoom) {
        chatRoomName = chatRoom.getName();
        category = chatRoom.getCategory();
        voteName = chatRoom.getVote().getVoteName();
    }


    //== 연관관계 메서드 ==//
    public void addHistoryMember(HistoryMember historyMember) {
        historyMembers.add(historyMember);
    }
}