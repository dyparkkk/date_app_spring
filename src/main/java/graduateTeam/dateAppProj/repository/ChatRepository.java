package graduateTeam.dateAppProj.repository;

import graduateTeam.dateAppProj.domain.ChatMessage;
import graduateTeam.dateAppProj.domain.ChatRoom;
import graduateTeam.dateAppProj.domain.MemberChatRoom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class ChatRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveChatroom(ChatRoom chatroom) {
        em.persist(chatroom);
    }
    public void saveChatMessage(ChatMessage chatMessage){
        em.persist(chatMessage);
    }
    public void saveMemberChatRoom(MemberChatRoom memberChatRoom) {
        em.persist(memberChatRoom);
    }

    public ChatRoom findById(UUID id) {
        return em.find(ChatRoom.class, id);
    }

    public List<ChatRoom> findAll() {
        return em.createQuery("select c from ChatRoom c", ChatRoom.class)
                .getResultList();
    }

    public List<MemberChatRoom> findMCAll(){
        return em.createQuery("select mc from MemberChatRoom mc", MemberChatRoom.class)
                .getResultList();
    }


}
