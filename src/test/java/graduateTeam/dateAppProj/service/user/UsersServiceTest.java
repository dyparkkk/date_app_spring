package graduateTeam.dateAppProj.service.user;


import graduateTeam.dateAppProj.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsersServiceTest {

    @Autowired
    UsersService usersService;

    @Test
    void testFindall(){
        //given
        Member member1 = new Member();
        
        usersService.save(member1);

        Member member2 = new Member();
        member2.setUsername("member2");
        usersService.save(member2);

        //when
        List<Member> findallmember = usersService.findAll();

        //then
        System.out.println("findallmember = " + findallmember);
    }


}
