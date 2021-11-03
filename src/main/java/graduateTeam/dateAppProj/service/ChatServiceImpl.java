package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.chat.dto.*;
import graduateTeam.dateAppProj.controller.dto.UserListInfoDto;
import graduateTeam.dateAppProj.domain.Vote;
import graduateTeam.dateAppProj.domain.VoteHistory;
import graduateTeam.dateAppProj.domain.VoteState;
import graduateTeam.dateAppProj.domain.chat.ChatMessage;
import graduateTeam.dateAppProj.domain.chat.ChatRoom;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.chat.MemberChatRoom;
import graduateTeam.dateAppProj.domain.chat.MessageType;
import graduateTeam.dateAppProj.repository.ChatRepository;
import graduateTeam.dateAppProj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public ChatMessageResponseDto sendMessage(ChatMessageRequestDto dto){
        log.info("ChatMessageRequestDto :" + dto.toString());

        if (dto.getType() == MessageType.SYSTEM) {
            return dto.toResponseDto("system");
        }

        Member member = memberRepository.findByUserId(dto.getSenderId())
                .orElseThrow(() -> {
                    log.warn("sendMessage : findByUserId");
                    throw new IllegalArgumentException("userId를 찾을수 없습니다. ");
                });

        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(dto.getRoomId()));

        ChatMessage chatMessage = ChatMessage.createChatMessage(chatRoom, dto.getSenderId(),
                member.getUsername(), dto.getMessage(), dto.getType());
        chatRepository.saveChatMessage(chatMessage);

        ChatMessageResponseDto chatMessageResponseDto = dto.toResponseDto(member.getUsername());
        log.info("chatMessageResponseDto: "+chatMessageResponseDto.toString());
        return chatMessageResponseDto;

    }

    @Transactional
    @Override
    public Long updateVote(String roomId, UpdateVoteRequestDto dto) {
        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(roomId));
        // if(member == owner ) ?
        chatRoom.getVote().updateVote(dto.getName(), dto.getState());

        return 1L;
    }

    @Transactional
    @Override
    public Long addVoteMember(String roomId, Member member){
        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(roomId));
        MemberChatRoom memberChatRoom = chatRepository.findMemberChatRoom(member, chatRoom).get();
        memberChatRoom.vote();
        chatRoom.getVote().addCount();
        return 1L;
    }

    @Transactional
    @Override
    public VoteInfoResponseDto voteInfo(String roomId) {
        // 유저 정보 포함
        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(roomId));
        VoteInfoResponseDto dto = new VoteInfoResponseDto(chatRoom.getVote());
        chatRepository.findMemberChatRoomByChatRoom(chatRoom)
                .stream().filter(memberChatRoom -> memberChatRoom.getIsVote() == Boolean.TRUE)
                .forEach(memberChatRoom -> dto.addUserList(memberChatRoom));
        return dto;
    }

    @Transactional
    @Override
    public Long endVote(String roomId) {
        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(roomId));
        chatRepository.findMemberChatRoomByChatRoom(chatRoom)
                .stream()
                .forEach(memberChatRoom -> {
                    if(memberChatRoom.getIsVote() == Boolean.TRUE){
                        VoteHistory vh = VoteHistory.builder()
                                .name(chatRoom.getName())
                                .member(memberChatRoom.getMember())
                                .category(chatRoom.getCategory())
                                .build();
                        chatRepository.saveVoteHistory(vh);
                    }
                });
        chatRoom.getVote().updateVote(VoteState.FINISH);
        return 1L;
    }

    @Transactional
    @Override
    public List<VoteHistoryAllDto> allHistory(){
        return chatRepository.allVoteHistory()
                .stream()
                .map(VoteHistoryAllDto::new)
                .collect(Collectors.toList());
    }
}
