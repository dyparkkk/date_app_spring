package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.dto.LoginRequestDto;
import graduateTeam.dateAppProj.controller.dto.UserInfoDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long save(UserInfoDto dto){
        validateDuplicateUser(dto.getUserId());

//        String bPwd =  passwordEncoder.encode(dto.getUserPwd());
//        dto.setUserPwd(bPwd);
        dto.setUserPwd(dto.getUserPwd());
        Member member = Member.createMember(dto);

        return memberRepository.save(member);
    }

    @Override
    public UserInfoDto login(LoginRequestDto dto, HttpServletRequest req) {
        Member member = checkLogin(dto.getUserId(), dto.getUserPwd());

        HttpSession session = req.getSession();
        session.setAttribute("user", member);
        return new UserInfoDto(member.getUsername(), member.getUserId(), null);
    }

    @Override
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findOne(id);
    }

    private void validateDuplicateUser(String userId){
        memberRepository.findByUserId(userId).ifPresent(findUser-> {
            throw new IllegalArgumentException("validateDuplicateUser : 이미 존재하는 userId 입니다. userId ="+ userId );
        });
    }

    private Member checkLogin(String userId, String userPwd) {
        Member findMember = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다. "));

//        if (!passwordEncoder.matches(userPwd, findMember.getUserPwd())) {
//            throw new IllegalArgumentException("비밀번호가 다릅니다. ");
//        }

        return findMember;
    }

}
