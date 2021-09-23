package graduateTeam.dateAppProj.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class ChatMessage {

    @Id @GeneratedValue
    private Long id;

    public enum MessageType {
        TALK, ENTER
    }
    private MessageType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private UUID roomId;

    private String sender;

    private String message;

    private LocalDateTime time;

    //== builder ==//
    @Builder
    public ChatMessage(UUID roomId, String sender, String message, LocalDateTime time) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.time = time;
    }
}
