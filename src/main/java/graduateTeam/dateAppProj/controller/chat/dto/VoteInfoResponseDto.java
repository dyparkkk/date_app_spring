package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.domain.Vote;
import graduateTeam.dateAppProj.domain.VoteState;
import graduateTeam.dateAppProj.domain.chat.ChatRoom;
import graduateTeam.dateAppProj.domain.chat.MemberChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class VoteInfoResponseDto {

    private VoteState state;

    private String name;

    private int count;

    private List<UserInfo> userList = new ArrayList<>();

    @Getter @Setter
    @NoArgsConstructor
    public class UserInfo{
        private String userId;
        private String userName;

        public UserInfo(String userId, String userName) {
            this.userId = userId;
            this.userName = userName;
        }
    }

    public VoteInfoResponseDto(Vote vote) {
        state = vote.getState();
        name = vote.getVoteName();
        count = vote.getCount();
    }

    //== 비즈니스 로직 ==//
    public void addUserList(MemberChatRoom mc) {
        UserInfo userInfo = new UserInfo(mc.getMember().getUserId(), mc.getMember().getUsername());
        userList.add(userInfo);
    }
}
