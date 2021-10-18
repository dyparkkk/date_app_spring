package graduateTeam.dateAppProj.domain;

import graduateTeam.dateAppProj.controller.chat.dto.RequestCreateChatRoomDto;
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

    @OneToMany(mappedBy = "chatroom")
    private List<ChatMessage> messages = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<MemberChatRoom> memberChatRooms = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(precision = 6)
    private double latitude;

    @Column(precision = 6)
    private double longitude;

    @Builder
    public ChatRoom(String name, Category category, double latitude, double longitude){
        this.name = name;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //== 연관관계 메서드==//
    public void addMemberChatRoom(MemberChatRoom memberChatRoom){
        memberChatRooms.add(memberChatRoom);
        memberChatRoom.addChatRoom(this);
    }

    public void addChatMessage(ChatMessage chatMessage) {
        messages.add(chatMessage);
    }

    //==생성 메서드==//
    public static ChatRoom createChatRoom(RequestCreateChatRoomDto dto, MemberChatRoom memberChatRoom) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
        chatRoom.addMemberChatRoom(memberChatRoom);
        return chatRoom;
    }


}
