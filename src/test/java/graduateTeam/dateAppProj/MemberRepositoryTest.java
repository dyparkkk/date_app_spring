package graduateTeam.dateAppProj;

import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void testMember() {
        //given
        Member member = new Member();
        member.setUsername("member1");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(savedId);

        //then
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    @Transactional
    void test_findByUserId() {
        String userId = "user1";

        Member member = new Member();
        member.setUsername("member1");
        member.setUserId(userId);

        //when
        memberRepository.save(member);
        List<Member> findMember = memberRepository.findByUserId(userId);

        //then
        Assertions.assertThat(findMember.get(0).getId()).isEqualTo(member.getId());

    }

}