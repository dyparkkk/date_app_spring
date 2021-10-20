package graduateTeam.dateAppProj.domain;

import graduateTeam.dateAppProj.domain.chat.MemberChatRoom;
import graduateTeam.dateAppProj.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberChatRoomTest {

    @Autowired private EntityManager em;
    @Autowired private MemberRepository memberRepository;


    @Test
    @Transactional
    void MC_생성() {
        //given
        Member member = new Member("name", "user11", "pwd");
        memberRepository.save(member);

        //when
        MemberChatRoom memberChatRoom = MemberChatRoom.createMemberChatRoom(member);

        //then
        Assertions.assertThat(memberChatRoom.getMember().getId()).isEqualTo(member.getId());
    }
}