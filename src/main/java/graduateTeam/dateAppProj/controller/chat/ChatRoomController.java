package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.dto.ChatRoomResponseDto;

import graduateTeam.dateAppProj.service.chat.ChatService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/chat")
@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatService chatService;

    @GetMapping("/rooms")
    public List<ChatRoomResponseDto> chatrooms() {
       return chatService.findAll();
    }

    @PostMapping("/createRoom")
    public ChatRoomResponseDto createChatRoom(@RequestParam String name) {
        String chatRoomId = chatService.createChatRoom(name);
        return chatService.findById(chatRoomId);
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomResponseDto roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }

    // ======== test controller ========= //

}
