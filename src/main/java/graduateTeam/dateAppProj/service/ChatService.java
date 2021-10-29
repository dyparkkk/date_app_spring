package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.chat.dto.*;
import graduateTeam.dateAppProj.domain.Member;

import java.util.List;

public interface ChatService {

    ChatMessageResponseDto sendMessage(ChatMessageRequestDto dto);
    Long updateVote(String roomId, UpdateVoteRequestDto dto, Member member);
    Long addVoteMember(String roomId, Member member);
    VoteInfoResponseDto voteInfo(String roomId);
    Long endVote(String roomId);
}
