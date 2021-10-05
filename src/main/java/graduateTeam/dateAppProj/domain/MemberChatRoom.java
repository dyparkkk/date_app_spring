package graduateTeam.dateAppProj.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MemberChatRoom {

    @Id @GeneratedValue
    @Column(name = "member_chatroom_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    // == 연관관계 메서드 ==//
    public void addChatRoom(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
    }

    //== 생성 메서드 ==//
    public static MemberChatRoom createMemberChatRoom(Member member){
        MemberChatRoom memberChatRoom = new MemberChatRoom();
        memberChatRoom.member = member;
        return memberChatRoom;
    }
}
