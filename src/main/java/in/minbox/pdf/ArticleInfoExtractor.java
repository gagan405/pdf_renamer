package in.minbox.pdf;

import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;

public interface ArticleInfoExtractor {

    Optional<ArticleInfo> extract(PDDocument document);

}
