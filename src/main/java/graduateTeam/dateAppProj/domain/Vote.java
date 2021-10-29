package graduateTeam.dateAppProj.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Embeddable
@Getter @NoArgsConstructor
public class Vote{

    @Enumerated(EnumType.STRING)
    private VoteState state;

    private String voteName;

    private Integer count;

    //== 내부 클래스 정의 ==//

    @Builder
    public Vote(String voteName, VoteState state) {
        this.voteName = voteName;
        this.state = state;
        count = 0;
    }

    //== 비즈니스 로직 ==/
    public void updateVote(String name, VoteState state) {
        this.voteName = name;
        this.state = state;
    }

    public void updateVote(VoteState state) {
        this.state = state;
    }

    public void addCount(){
        count += 1;
    }

}
