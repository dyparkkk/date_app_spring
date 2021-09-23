package graduateTeam.dateAppProj.controller;

import graduateTeam.dateAppProj.controller.dto.JsonTestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private final String template = "Hello, %s";

    @GetMapping("/jsontest")
    public JsonTestDto jsonTest(@RequestParam(value = "name", defaultValue = "User") String name) {
        return new JsonTestDto(String.format(template, name));
    }
}
