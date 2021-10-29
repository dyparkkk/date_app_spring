package graduateTeam.dateAppProj.domain.chat;

import graduateTeam.dateAppProj.controller.chat.dto.RequestCreateChatRoomDto;
import graduateTeam.dateAppProj.domain.Vote;
import graduateTeam.dateAppProj.domain.VoteState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ChatRoom {

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "chatroom_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;
    private String ownerId;
    private int userNumber;

    @Embedded
    private Vote vote;

    @OneToMany(mappedBy = "chatroom", cascade = {CascadeType.REMOVE})
    private List<ChatMessage> messages = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<MemberChatRoom> memberChatRooms = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(precision = 6)
    private double latitude;

    @Column(precision = 6)
    private double longitude;

    //==생성 메서드==//
    @Builder
    public ChatRoom(String name, Category category, double latitude, double longitude,
                    String ownerId, Vote vote){
        this.name = name;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ownerId = ownerId;
        this.userNumber = 0;
        this.vote = vote;
    }

    //== 연관관계 메서드==//
    public ChatRoom addMemberChatRoom(MemberChatRoom memberChatRoom){
        memberChatRooms.add(memberChatRoom);
        memberChatRoom.addChatRoom(this);
        userNumber += 1;
        return this;
    }

    public void removeMemberChatRoom(MemberChatRoom memberChatRoom) {
        memberChatRooms.remove(memberChatRoom);
        userNumber -= 1;
    }

    public void addChatMessage(ChatMessage chatMessage) {
        messages.add(chatMessage);
    }

    //== 비즈니스 로직==//

}
