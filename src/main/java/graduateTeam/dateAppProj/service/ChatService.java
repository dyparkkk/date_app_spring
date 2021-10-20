package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.chat.dto.ChatMessageRequestDto;
import graduateTeam.dateAppProj.controller.chat.dto.ChatMessageResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.RequestCreateChatRoomDto;
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
public class ChatService {

    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String createChatRoom(RequestCreateChatRoomDto dto) {

        Member member = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> {
                    log.warn("createChatRoom : findByUserId");
                    throw  new IllegalArgumentException("userId를 찾을 수 없습니다.");
                });
        MemberChatRoom memberChatRoom = MemberChatRoom.createMemberChatRoom(member);
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
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.warn("enterChatRoom : findByUserId");
                    throw  new IllegalArgumentException("userId를 찾을수 없습니다. ");
                });
        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(roomId));

        if(chatRepository.findMemberChatRoom(member, chatRoom).isPresent() == false){
            MemberChatRoom memberChatRoom = MemberChatRoom.createMemberChatRoom(member);
            chatRepository.saveMemberChatRoom(memberChatRoom);
            chatRoom.addMemberChatRoom(memberChatRoom);
        }

        return new ChatRoomResponseDto(chatRoom);
    }

    @Transactional
    public ChatMessageResponseDto sendMessage(ChatMessageRequestDto dto){

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

        return dto.toResponseDto(member.getUsername());
    }

    @Transactional
    public Long leaveChatRoom(String roomId, String userId){
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.warn("enterChatRoom : findByUserId");
                    throw  new IllegalArgumentException("userId를 찾을수 없습니다. ");
                });
        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(roomId));

        MemberChatRoom memberChatRoom = chatRepository.findMemberChatRoom(member, chatRoom)
                .orElseThrow(() -> {
                    log.warn("leaveChatRoom : findMemberChatRoom");
                    throw new IllegalArgumentException("memberChatRoom을 찾을수 없습니다. ");
                });
        chatRoom.removeMemberChatRoom(memberChatRoom);
        chatRepository.removeMemberChatRoom(memberChatRoom);

        if(chatRoom.getUserNumber() == 0){
            chatRepository.removeChatRoom(chatRoom);
        }
        return 1L;
    }

}
