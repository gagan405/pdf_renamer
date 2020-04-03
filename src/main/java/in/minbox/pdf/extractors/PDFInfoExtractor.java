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
            articleInfo.setTitle(information.getTitle());
            articleInfo.setAuthors(Collections.singletonList(information.getAuthor()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.of(new ArticleInfo());
    }

    @Override
    public Optional<ArticleInfo> extract(PDDocument document) {
        if (!document.isEncrypted()) {
            ArticleInfo articleInfo = new ArticleInfo();
            PDDocumentInformation information = document.getDocumentInformation();
            articleInfo.setTitle(information.getTitle());
            articleInfo.setAuthors(Collections.singletonList(information.getAuthor()));
            return Optional.of(articleInfo);
        }
        System.out.println("Could not extract article information from PDF information. Possible that the PDF creator did not set the file information.");
        return Optional.empty();
    }
}