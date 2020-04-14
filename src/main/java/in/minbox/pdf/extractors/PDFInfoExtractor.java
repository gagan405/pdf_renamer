package in.minbox.pdf.extractors;

import java.util.Collections;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import in.minbox.pdf.ArticleInfo;
import in.minbox.pdf.ArticleInfoExtractor;
import lombok.RequiredArgsConstructor;

/**
 * Reads PDFInfo from the file metadata and reads title from the metadata if exists
 */
@RequiredArgsConstructor
public class PDFInfoExtractor implements ArticleInfoExtractor {

    private final int maxLength;

    @Override
    public Optional<ArticleInfo> extract(PDDocument document) {
        ArticleInfo articleInfo = new ArticleInfo();
        PDDocumentInformation information = document.getDocumentInformation();
        if(information.getTitle() != null && !information.getTitle().isEmpty()) {
            int indexOfAuthors = information.getTitle().indexOf("pdfauthor"); // in case of LaTeX
            String title = indexOfAuthors > 0 ?
                information.getTitle().substring(0, information.getTitle().indexOf("pdfauthor")).trim() :
                information.getTitle();
            articleInfo.setTitle(title.substring(0, Math.min(title.length(), maxLength)));
            articleInfo.setAuthors(Collections.singletonList(information.getAuthor()));
            return Optional.of(articleInfo);
        } else {
            return Optional.empty();
        }
    }
}
