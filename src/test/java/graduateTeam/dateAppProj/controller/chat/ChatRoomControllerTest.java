package graduateTeam.dateAppProj.controller.chat;

import graduateTeam.dateAppProj.controller.dto.RequestCreateChatRoomDto;
import graduateTeam.dateAppProj.domain.Category;
import graduateTeam.dateAppProj.domain.ChatRoom;
import graduateTeam.dateAppProj.domain.Member;
import graduateTeam.dateAppProj.domain.MemberChatRoom;
import graduateTeam.dateAppProj.repository.ChatRepository;
import graduateTeam.dateAppProj.repository.MemberRepository;
import graduateTeam.dateAppProj.service.ChatService;
import graduateTeam.dateAppProj.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatRoomControllerTest {

    @LocalServerPort
    private int port;

    @Autowired private TestRestTemplate restTemplate;
    @Autowired private ChatService chatService;
    @Autowired private ChatRepository chatRepository;
    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;

    @Test
    @Transactional
    void 채팅룸생성() throws Exception{
        //given
        Member member = new Member();
        member.setUserId("user11");
        member.setUsername("user1");
        memberRepository.save(member);

        RequestCreateChatRoomDto dto = new RequestCreateChatRoomDto();
        dto.setName("채팅방");
        dto.setUserId(member.getUserId());
        dto.setLatitude(123.123);
        dto.setLongitude(321.321);
        dto.setCategory(Category.date);

        System.out.println("member.userId = " + member.getUserId());
        System.out.println(dto.toString());


        String url = "http://localhost:" + port + "/chat/createRoom";

        //when
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, dto, String.class);
//
        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan("");
//
//        List<ChatRoom> chatRoomList = chatRepository.findAll();
//        assertThat(chatRoomList.get(0).getName()).isEqualTo("채팅방");
//
//        List<MemberChatRoom> mcAll = chatRepository.findMCAll();
//        assertThat(mcAll.get(0).getMember().getId()).isEqualTo(member.getId());
    }

}