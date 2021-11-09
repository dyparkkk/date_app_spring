package graduateTeam.dateAppProj.domain.history;

import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.chat.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@NoArgsConstructor
@Slf4j
public class HistoryMember {
    @Id
    @GeneratedValue
    @Column(name = "history_member_id")
    private Long id;

    private BigDecimal score;

    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public HistoryMember(History history, Member member) {
        this.score = new BigDecimal("3.00");
        this.history = history;
        this.member = member;
    }

    //== 비즈니스 로직 ==//
    public void addScore(double x) {
        /**
         *     x = (x-3)/10
         *     score += x
         */

        x = x - 3.0;
        x /= 10.0;
        score = score.add(new BigDecimal(x));
    }
}
