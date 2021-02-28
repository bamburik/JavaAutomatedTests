package BusinessObjects;

import lombok.Data;
import java.util.List;

@Data
public class Pet {

    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private Status status;


    public Pet(Category category, String name, List<String> photoUrls, List<Tag> tags, Status status) {
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }
}
