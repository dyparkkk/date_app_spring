package graduateTeam.dateAppProj.service.user;

import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final MemberRepository memberRepository;

    public Long save(Member member){
        return memberRepository.save(member);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findOne(id);
    }

}
