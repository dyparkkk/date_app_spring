package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.dto.ChatRoomIdResponseDto;
import graduateTeam.dateAppProj.controller.dto.ChatRoomResponseDto;

import graduateTeam.dateAppProj.controller.dto.RequestCreateChatRoomDto;
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
    public ChatRoomResponseDto roomInfo(@PathVariable String roomId,
                                        @RequestParam String userId) {
        return chatService.enterChatRoom(roomId, userId);
    }
    /**
     * 추가 사항 :
     *      이미 memberChatRoom이 있다면?
     *      chatRoom 안의 chatMessage 로딩
     */
}
