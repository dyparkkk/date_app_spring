package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.dto.ChatMessageDto;
import graduateTeam.dateAppProj.controller.dto.ChatRoomResponseDto;
import graduateTeam.dateAppProj.controller.dto.RequestCreateChatRoomDto;
import graduateTeam.dateAppProj.domain.ChatMessage;
import graduateTeam.dateAppProj.domain.ChatRoom;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.MemberChatRoom;
import graduateTeam.dateAppProj.repository.ChatRepository;
import graduateTeam.dateAppProj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String createChatRoom(RequestCreateChatRoomDto dto) {

        List<Member> memberList = memberRepository.findByUserId(dto.getUserId());
        MemberChatRoom memberChatRoom = MemberChatRoom.createMemberChatRoom(memberList.get(0));
        chatRepository.saveMemberChatRoom(memberChatRoom);

        ChatRoom chatRoom = ChatRoom.createChatRoom(dto, memberChatRoom);
        chatRepository.saveChatroom(chatRoom);
        return chatRoom.getId().toString();
    }

    @Transactional
    public ChatRoomResponseDto findById(String id) {
        UUID uuid = UUID.fromString(id);
        return new ChatRoomResponseDto(chatRepository.findById(uuid));
    }

    @Transactional
    public List<ChatRoomResponseDto> findAll() {
        return chatRepository.findAll().stream().map(ChatRoomResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ChatRoomResponseDto enterChatRoom(String roomId, String userId) {
        List<Member> memberList = memberRepository.findByUserId(userId);
        MemberChatRoom memberChatRoom = MemberChatRoom.createMemberChatRoom(memberList.get(0));
        chatRepository.saveMemberChatRoom(memberChatRoom);

        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(roomId));
        chatRoom.addMemberChatRoom(memberChatRoom);
        return new ChatRoomResponseDto(chatRoom);
    }

    @Transactional
    public Long sendMessage(ChatMessageDto dto){
        List<Member> memberList = memberRepository.findByUserId(dto.getSenderId());
        Member member = memberList.get(0);
        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(dto.getRoomId()));

        ChatMessage chatMessage = ChatMessage.createChatMessage(chatRoom, dto.getSenderId(), member.getUsername(), dto.getMessage());
        chatRepository.saveChatMessage(chatMessage);
        return chatMessage.getId();
    }

}
