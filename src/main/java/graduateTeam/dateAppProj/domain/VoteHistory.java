package graduateTeam.dateAppProj.domain;

import graduateTeam.dateAppProj.domain.chat.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class VoteHistory {
    @Id @GeneratedValue
    @Column(name = "vote_history_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public VoteHistory(Long id, String name, Category category, Member member) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.member = member;
    }
}
