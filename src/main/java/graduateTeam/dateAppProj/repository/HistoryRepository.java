package graduateTeam.dateAppProj.repository;

import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.history.History;
import graduateTeam.dateAppProj.domain.history.HistoryMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HistoryRepository {
    @PersistenceContext
    private EntityManager em;

    public void saveHistoryMember(HistoryMember historyMember) {
        em.persist(historyMember);
    }

    public void saveHistory(History history) {
        em.persist(history);
    }

    public History findById(Long id) {
        return em.find(History.class, id);
    }

    public List<HistoryMember> findHistoryMemberByMember(Member member) {
        return em.createQuery("select hm from HistoryMember hm" +
                        " where hm.member = :member", HistoryMember.class)
                .setParameter("member", member)
                .getResultList();
    }

}
