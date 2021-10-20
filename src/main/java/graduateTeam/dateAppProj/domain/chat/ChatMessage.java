package graduateTeam.dateAppProj.domain.chat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    private MessageType type;

    //== builder ==//
    private ChatMessage(ChatRoom chatroom, String senderId, String senderName, String message, MessageType type) {
        this.chatroom = chatroom;
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
        this.time = LocalDateTime.now();
        this.type = type;
    }

    public static ChatMessage createChatMessage(ChatRoom chatRoom,  String senderId,
                                                String senderName, String message,
                                                MessageType type){
        ChatMessage chatMessage = new ChatMessage(chatRoom, senderId, senderName, message, type);
        chatRoom.addChatMessage(chatMessage);
        return chatMessage;
    }
}
