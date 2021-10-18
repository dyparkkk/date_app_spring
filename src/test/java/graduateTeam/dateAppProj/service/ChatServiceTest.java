package graduateTeam.dateAppProj.service;

import graduateTeam.dateAppProj.repository.ChatRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ChatServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    ChatService chatService;
    @Autowired
    ChatRepository chatRepository;

//    @Test
//    @Transactional
//    void createMC() {
//        //given
//        Member member = new Member();
//        member.setUserId("1234");
//        member.setUsername("user1");
//        memberService.save(member);
//
//        RequestCreateChatRoomDto dto = new RequestCreateChatRoomDto();
//        dto.setName("채팅방");
//        dto.setUserId(member.getUserId());
//        dto.setLatitude(123.123);
//        dto.setLongitude(321.321);
//        dto.setCategory(Category.date);
//
//        dto.toString();
//
//        chatService.createChatRoom(dto);
//
//        List<MemberChatRoom> mcAll = chatRepository.findMCAll();
//
//        Assertions.assertThat(mcAll.get(0).getMember().getUserId()).isEqualTo("1234");
//        Assertions.assertThat(mcAll.get(0).getMember().getId()).isEqualTo(member.getId());
//}
}