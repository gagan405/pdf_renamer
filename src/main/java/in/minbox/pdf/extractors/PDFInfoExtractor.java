package in.minbox.pdf.extractors;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import in.minbox.pdf.ArticleInfo;
import in.minbox.pdf.ArticleInfoExtractor;
import lombok.NonNull;

public class PDFInfoExtractor implements ArticleInfoExtractor {

    public Optional<ArticleInfo> extractHeader(@NonNull String absolutePath) {
        ArticleInfo articleInfo = new ArticleInfo();
        try (PDDocument document = PDDocument.load(new File(absolutePath))) {
            PDDocumentInformation information = document.getDocumentInformation();
            String title = information.getTitle().substring(0, information.getTitle().indexOf("pdfauthor")).trim();
            articleInfo.setTitle(title);
            articleInfo.setAuthors(Collections.singletonList(information.getAuthor()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.of(new ArticleInfo());
    }

    @Override
    public Optional<ArticleInfo> extract(PDDocument document) {
        ArticleInfo articleInfo = new ArticleInfo();
        PDDocumentInformation information = document.getDocumentInformation();
        if(information.getTitle() != null && !information.getTitle().isEmpty()) {
            String title = information.getTitle().substring(0, information.getTitle().indexOf("pdfauthor")).trim();
            articleInfo.setTitle(title);
            articleInfo.setAuthors(Collections.singletonList(information.getAuthor()));
            return Optional.of(articleInfo);
        } else {
            return Optional.empty();
        }
    }
}
