package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.dto.LoginInfoDto;
import graduateTeam.dateAppProj.controller.dto.UserInfoDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(UserInfoDto dto){
        validateDuplicateUser(dto.getUserId());

        String bPwd =  passwordEncoder.encode(dto.getUserPwd());
        dto.setUserPwd(bPwd);
        Member member = Member.createMember(dto);

        return memberRepository.save(member);
    }

    public Long login(LoginInfoDto dto, HttpServletRequest req) {
        checkLogin(dto.getUserId(), dto.getUserPwd());

        HttpSession session = req.getSession();
        session.setAttribute("user", dto.getUserId());

        return 1L;
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findOne(id);
    }

    private void validateDuplicateUser(String userId){
        memberRepository.findByUserId(userId).ifPresent(findUser-> {
            throw new IllegalArgumentException("validateDuplicateUser : 이미 존재하는 userId 입니다. userId ="+ userId );
        });
    }

    private void checkLogin(String userId, String userPwd) {
        Member findMember = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다. "));

        if (!passwordEncoder.matches(userPwd, findMember.getUserPwd())) {
            throw new IllegalArgumentException("비밀번호가 다릅니다. ");
        }
    }

}
