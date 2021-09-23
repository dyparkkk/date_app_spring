package graduateTeam.dateAppProj.repository;

import graduateTeam.dateAppProj.domain.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class ChatRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(ChatRoom chatroom) {
        em.persist(chatroom);
    }

    public ChatRoom findById(UUID id) {
        return em.find(ChatRoom.class, id);
    }

    public List<ChatRoom> findAll() {
        return em.createQuery("select c from ChatRoom c", ChatRoom.class)
                .getResultList();
    }
}
