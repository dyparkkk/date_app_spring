package graduateTeam.dateAppProj.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class ChatRoom {

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "chatroom_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

//    @OneToMany(mappedBy = "roomId")
//    private List<ChatMessage> messages = new ArrayList<>();


    //==생성 메서드==//
    public static ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        return chatRoom;
    }
}
