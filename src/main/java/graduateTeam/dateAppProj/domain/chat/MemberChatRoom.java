package graduateTeam.dateAppProj.domain.chat;

import graduateTeam.dateAppProj.domain.Member;
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

    private Boolean isVote;

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
        memberChatRoom.isVote = Boolean.FALSE;
        return memberChatRoom;
    }

    //== 비즈니스 로직 ==//
    public void vote(){
        isVote = Boolean.TRUE;
    }
}
