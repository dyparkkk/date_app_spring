package graduateTeam.dateAppProj.service.chat;

import graduateTeam.dateAppProj.controller.dto.ChatRoomResponseDto;
import graduateTeam.dateAppProj.domain.ChatRoom;
import graduateTeam.dateAppProj.repository.ChatRepository;
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

    @Transactional
    public String createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.createChatRoom(name);
        chatRepository.save(chatRoom);
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

}
