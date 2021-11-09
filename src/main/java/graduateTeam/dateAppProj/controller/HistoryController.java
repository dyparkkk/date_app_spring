package graduateTeam.dateAppProj.controller;

import graduateTeam.dateAppProj.controller.dto.HistoryUserListDto;
import graduateTeam.dateAppProj.controller.dto.MyHistoryDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.service.HistoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("")
    public List<MyHistoryDto> allHistory(@SessionAttribute(name = "user", required = false) Member loginMember) {
        return historyService.allHistory(loginMember);
    }

    @GetMapping("/{id}")
    public List<HistoryUserListDto> historyInfo(@SessionAttribute(name = "user", required = false) Member loginMember,
                                                @PathVariable String id) {

        return historyService.historyInfo(loginMember, id);
    }

    @PostMapping("/{id}")
    public String evaluationHistory(@SessionAttribute(name = "user", required = false) Member loginMember,
                                    @PathVariable String id,
                                    @RequestBody HistoryUserListDto param) {

        historyService.evaluationHistory(id, param.getMainList());
        return "평가완료";
    }
}
