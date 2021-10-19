package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomIdResponseDto;
import graduateTeam.dateAppProj.controller.chat.dto.ChatRoomResponseDto;

import graduateTeam.dateAppProj.controller.chat.dto.RequestCreateChatRoomDto;
import graduateTeam.dateAppProj.service.ChatService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/chat")
@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatService chatService;

    @GetMapping("/rooms")
    public List<ChatRoomResponseDto> chatRooms() {
        return chatService.findAll();
    }

    @PostMapping("/createRoom")
    public ChatRoomIdResponseDto createChatRoom(@RequestBody RequestCreateChatRoomDto dto) {
        return new ChatRoomIdResponseDto(chatService.createChatRoom(dto));
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomResponseDto enterChatRoom(@PathVariable String roomId,
                                        @RequestParam String userId) {
        return chatService.enterChatRoom(roomId, userId);
    }
    /**
     * 추가 사항 :
     *      이미 memberChatRoom이 있다면?
     *      chatRoom 안의 chatMessage 로딩
     */

//    @GetMapping("/search")
//    public List<ChatRoomResponseDto> searchChatRoom (@RequestParam String word,
//                                                     @RequestParam Category category) {
//
//
//    }

    @GetMapping("/leaveRoom/{roomId}")
    public Long leaveChatRoom(@PathVariable String roomId,
                              @RequestParam String userId) {
        return chatService.leaveChatRoom(roomId, userId);
    }
}
