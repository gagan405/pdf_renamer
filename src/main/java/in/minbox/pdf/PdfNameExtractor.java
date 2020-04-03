package in.minbox.pdf;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PdfNameExtractor {

    private final List<ArticleInfoExtractor> articleInfoExtractors;

    public Optional<ArticleInfo> extract(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            if(document.isEncrypted()) {
                System.out.println("Cannot read encrypted PDF. Exiting.");
                System.exit(-2);
            }

            Optional<ArticleInfo> articleInfo = articleInfoExtractors.stream().map(extractor -> extractor.extract(document))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

            return articleInfo;
        } catch (IOException e) {
            System.out.println("Error reading PDF file. " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
