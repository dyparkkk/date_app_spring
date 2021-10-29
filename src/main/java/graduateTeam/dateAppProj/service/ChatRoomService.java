package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomInfoResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.RequestCreateChatRoomDto;
import graduateTeam.dateAppProj.domain.Member;

import java.util.List;

public interface ChatRoomService {
    String createChatRoom(RequestCreateChatRoomDto dto);
    ChatRoomResponseDto findById(String id);
    List<ChatRoomResponseDto> findAll();
    ChatRoomResponseDto enterChatRoom(String roomId, String userId);
    Long leaveChatRoom(String roomId, String userId);

    ChatRoomInfoResponseDto getChatRoomInfo(String roomId);

    List<String> FindChatRoomByMember(Member member);

}
