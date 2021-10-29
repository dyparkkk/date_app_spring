package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomIdResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomInfoResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.RequestCreateChatRoomDto;
import graduateTeam.dateAppProj.service.ChatRoomService;
import graduateTeam.dateAppProj.service.ChatService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chat")
@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/rooms")
    public List<ChatRoomResponseDto> chatRooms() {
        return chatRoomService.findAll();
    }

    @PostMapping("/createRoom")
    public ChatRoomIdResponseDto createChatRoom(@RequestBody RequestCreateChatRoomDto dto) {
        return new ChatRoomIdResponseDto(chatRoomService.createChatRoom(dto));
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomResponseDto enterChatRoom(@PathVariable String roomId,
                                        @RequestParam String userId) {
        return chatRoomService.enterChatRoom(roomId, userId);
    }
    /**
     * 추가 사항 :
     *      chatRoom 안의 chatMessage 로딩
     */

    @GetMapping("/leaveRoom/{roomId}")
    public Long leaveChatRoom(@PathVariable String roomId,
                              @RequestParam String userId) {
        return chatRoomService.leaveChatRoom(roomId, userId);
    }

    @GetMapping("/roomInfo/{roomId}")
    public ChatRoomInfoResponseDto chatRoomInfo(@PathVariable String roomId){
        return chatRoomService.getChatRoomInfo(roomId);
    }

}
