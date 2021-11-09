package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.dto.HistoryUserListDto;
import graduateTeam.dateAppProj.controller.dto.MyHistoryDto;
import graduateTeam.dateAppProj.controller.dto.ViewUserScoreResponseDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.history.History;
import graduateTeam.dateAppProj.domain.history.HistoryMember;
import graduateTeam.dateAppProj.repository.HistoryRepository;
import graduateTeam.dateAppProj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<MyHistoryDto> allHistory(Member member) {
        List<HistoryMember> list = historyRepository.findHistoryMemberByMember(member);
        ArrayList<MyHistoryDto> responseList = new ArrayList<>();
        for (HistoryMember hm : list) {
            responseList.add(new MyHistoryDto(hm.getHistory(), hm));
        }

        return responseList;
    }

    @Transactional
    public List<HistoryUserListDto> historyInfo(Member member, String id) {
        History history = historyRepository.findById(Long.valueOf(id));
        List<HistoryUserListDto> list = new ArrayList<>();
        for(HistoryMember hm : history.getHistoryMembers()){
            if( hm.getMember() != member){
                list.add(new HistoryUserListDto( hm.getMember()));
            }
        }

        return list;
    }

    @Transactional
    public void evaluationHistory(String id, List<HistoryUserListDto> list) {
        History history = historyRepository.findById(Long.valueOf(id));
        for(HistoryUserListDto dto : list){
            for(HistoryMember hm : history.getHistoryMembers()){
                if(hm.getMember().getUserId().equals(dto.getUserId())){
                    log.info("addScore : "+ dto.getScore());
                    hm.addScore(dto.getScore());
                }
            }
        }
    }

    @Transactional
    public void updateMyScore(Member member) {
        List<HistoryMember> hmList = historyRepository.findHistoryMemberByMember(member);
        BigDecimal score = new BigDecimal("3.00");
        for (HistoryMember hm : hmList) {
            double tmp = hm.getScore().doubleValue();
            tmp = (tmp-3.0)/10.0;
            score = score.add(new BigDecimal(tmp));
        }
//        score.setScale(2, RoundingMode.HALF_UP);
        member.setScore(score);
    }

    @Transactional
    public ViewUserScoreResponseDto viewUserScore(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.warn("viewUserScore : findByUserId");
                    throw  new IllegalArgumentException("userId를 찾을수 없습니다. ");
                });

        return new ViewUserScoreResponseDto(member);
    }
}
