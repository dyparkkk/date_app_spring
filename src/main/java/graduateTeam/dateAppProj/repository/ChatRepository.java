package graduateTeam.dateAppProj.repository;

import graduateTeam.dateAppProj.domain.Vote;
import graduateTeam.dateAppProj.domain.VoteHistory;
import graduateTeam.dateAppProj.domain.chat.Category;
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

    public List<MemberChatRoom> findMemberChatRoomByChatRoom(ChatRoom chatRoom) {
        return em.createQuery("select mc from MemberChatRoom mc" +
                " where mc.chatRoom = :chatRoom", MemberChatRoom.class)
                .setParameter("chatRoom", chatRoom)
                .getResultList();
    }

    public List<MemberChatRoom> findMemberChatRoomByMember(Member member) {
        return em.createQuery("select mc from MemberChatRoom mc" +
                        " where mc.member = :member", MemberChatRoom.class)
                .setParameter("member", member)
                .getResultList();
    }

    public void saveVote(Vote vote) {
        em.persist(vote);
    }

    public void saveVoteHistory(VoteHistory voteHistory) {
        em.persist(voteHistory);
    }

    public List<VoteHistory> allVoteHistory(){
        return em.createQuery("select h from VoteHistory h", VoteHistory.class)
                .getResultList();
    }

    public List<ChatRoom> findAllByCategory(Category category){
        return em.createQuery("select cr from ChatRoom cr"+
                " where cr.category = :category", ChatRoom.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<ChatRoom> findAllContaining(String str){
        return em.createQuery("select cr from ChatRoom cr" +
                " where cr.name like :str", ChatRoom.class)
                .setParameter("str", "%"+str+"%")
                .getResultList();
    }

}
