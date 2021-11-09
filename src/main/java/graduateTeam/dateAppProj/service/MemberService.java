package graduateTeam.dateAppProj.service;


import graduateTeam.dateAppProj.controller.dto.LoginRequestDto;
import graduateTeam.dateAppProj.controller.dto.UserInfoDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberService {

    // 회원가입
    Long save(UserInfoDto dto);

    // 로그인
    UserInfoDto login(LoginRequestDto dto, HttpServletRequest req);

    List<Member> findAll();

    Member findById(Long id) ;

}
