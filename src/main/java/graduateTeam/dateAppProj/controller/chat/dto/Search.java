package graduateTeam.dateAppProj.controller.chat.dto;

import graduateTeam.dateAppProj.domain.chat.Category;
import lombok.Data;

public class Search {

    @Data
    public static class CategoryDto{
        private Category category;
    }

    @Data
    public static class ContainingDto{
        private String name;
    }
}
