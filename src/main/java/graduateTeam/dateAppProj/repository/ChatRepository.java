package graduateTeam.dateAppProj.repository;

import graduateTeam.dateAppProj.domain.chat.ChatMessage;
import graduateTeam.dateAppProj.domain.chat.ChatRoom;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.chat.MemberChatRoom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
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

    public Optional<MemberChatRoom> findMemberChatRoom(Member member, ChatRoom chatRoom) {
        return Optional.ofNullable(
                em.createQuery("select mc from MemberChatRoom mc" +
                        " where mc.member = :member" +
                                " and mc.chatRoom = :chatRoom", MemberChatRoom.class)
                        .setParameter("member", member)
                        .setParameter("chatRoom", chatRoom)
                        .getResultList().stream().findFirst().orElse(null));
    }

    public void removeMemberChatRoom(MemberChatRoom mc) {
        em.remove(mc);
    }

    public void removeChatRoom(ChatRoom chatRoom){
        em.remove(chatRoom);
    }

}
