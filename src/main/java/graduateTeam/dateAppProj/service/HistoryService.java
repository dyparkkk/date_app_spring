package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.dto.HistoryUserListDto;
import graduateTeam.dateAppProj.controller.dto.MyHistoryDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.history.History;
import graduateTeam.dateAppProj.domain.history.HistoryMember;
import graduateTeam.dateAppProj.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    @Transactional
    public List<MyHistoryDto> allHistory(Member member) {
        return member.getHistoryMembers()
                .stream()
                .map(historyMember -> new MyHistoryDto(historyMember.getHistory()))
                .collect(Collectors.toList());
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
            HistoryMember historyMember = history.getHistoryMembers().stream()
                    .filter(hm -> hm.getMember().getUserId().equals(dto.getUserId()))
                    .collect(Collectors.toList())
                    .get(0);
            historyMember.addScore(dto.getScore());
        }
    }
}