package graduateTeam.dateAppProj.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
public class ChatMessage {

    @Id @GeneratedValue
    @Column(name = "chat_message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatroom;

    private String senderId;

    private String senderName;

    private String message;

    private LocalDateTime time;

    //== builder ==//
    private ChatMessage(ChatRoom chatroom, String senderId, String senderName, String message) {
        this.chatroom = chatroom;
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public static ChatMessage createChatMessage(ChatRoom chatRoom,  String senderId, String senderName, String message){
        ChatMessage chatMessage = new ChatMessage(chatRoom, senderId, senderName, message);
        chatRoom.addChatMessage(chatMessage);
        return chatMessage;
    }
}
