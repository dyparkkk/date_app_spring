package graduateTeam.dateAppProj.service.user;


import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.repository.MemberRepository;
import graduateTeam.dateAppProj.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void testFindAll(){
        //given
//        Member member1 = new Member();
//
//        usersService.save(member1);
//
//        Member member2 = new Member();
//        member2.setUsername("member2");
//        usersService.save(member2);
//
//        //when
//        List<Member> findallmember = usersService.findAll();
//
//        //then
//        System.out.println("findallmember = " + findallmember);
    }

    

}
