package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomInfoResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.RequestCreateChatRoomDto;
import graduateTeam.dateAppProj.controller.dto.UserListInfoDto;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.Vote;
import graduateTeam.dateAppProj.domain.chat.Category;
import graduateTeam.dateAppProj.domain.chat.ChatRoom;
import graduateTeam.dateAppProj.domain.chat.MemberChatRoom;
import graduateTeam.dateAppProj.repository.ChatRepository;
import graduateTeam.dateAppProj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String createChatRoom(RequestCreateChatRoomDto dto) {

        Member member = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> {
                    log.warn("createChatRoom : findByUserId");
                    throw  new IllegalArgumentException("userId를 찾을 수 없습니다.");
                });

        // memberChatRoom 생성
        MemberChatRoom memberChatRoom = MemberChatRoom.createMemberChatRoom(member);
        chatRepository.saveMemberChatRoom(memberChatRoom);

        //chatRoom 생성
        ChatRoom chatRoom = dto.toEntity()
                .addMemberChatRoom(memberChatRoom);
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
    public Long leaveChatRoom(String roomId, String userId){
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.warn("enterChatRoom : findByUserId");
                    throw  new IllegalArgumentException("userId를 찾을수 없습니다. ");
                });
        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(roomId));
        log.info("leaveChatRoom - chatroom :  " + chatRoom.getId());

        MemberChatRoom memberChatRoom = chatRepository.findMemberChatRoom(member, chatRoom)
                .orElseThrow(() -> {
                    log.warn("leaveChatRoom : findMemberChatRoom");
                    throw new IllegalArgumentException("memberChatRoom을 찾을수 없습니다. ");
                });
        log.info("leaveChatRoom - memberChatRoom: "+memberChatRoom.getId());
        chatRoom.removeMemberChatRoom(memberChatRoom);
        chatRepository.removeMemberChatRoom(memberChatRoom);

        if(chatRoom.getUserNumber() == 0){
            chatRepository.removeChatRoom(chatRoom);
        }
        return 1L;
    }

    @Transactional
    public ChatRoomInfoResponseDto getChatRoomInfo(String roomId) {
        ChatRoom chatRoom = chatRepository.findById(UUID.fromString(roomId));
        List<UserListInfoDto> userList = chatRepository.findMemberChatRoomByChatRoom(chatRoom)
                .stream().map(mc -> new UserListInfoDto().createDto(mc.getMember()))
                .collect(Collectors.toList());

        return new ChatRoomInfoResponseDto().createDto(chatRoom, userList);
    }

    @Transactional
    public List<String> FindChatRoomByMember(Member member) {
        /**
         * 유저 정보로 유저가 들어가있는 방 찾기
         */

        List<String> roomIdList = chatRepository.findMemberChatRoomByMember(member)
                .stream().map(mc -> mc.getChatRoom().getId().toString())
                .collect(Collectors.toList());

        return roomIdList;
    }

    @Transactional
    public List<ChatRoomResponseDto> findAllByCategory(Category category) {
        return chatRepository.findAllByCategory(category).stream()
                .map(ChatRoomResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ChatRoomResponseDto> findAllByContaining(String str) {
        return chatRepository.findAllContaining(str).stream()
                .map(ChatRoomResponseDto::new)
                .collect(Collectors.toList());
    }
}
