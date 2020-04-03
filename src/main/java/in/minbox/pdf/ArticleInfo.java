package in.minbox.pdf;

import java.util.List;

import lombok.Data;

@Data
public class ArticleInfo {
    private String title;
    private List<String> authors;
}
