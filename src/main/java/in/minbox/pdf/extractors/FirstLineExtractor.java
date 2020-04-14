package in.minbox.pdf.extractors;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import in.minbox.pdf.ArticleInfo;
import in.minbox.pdf.ArticleInfoExtractor;
import lombok.RequiredArgsConstructor;

/**
 * Reads the first page of the PDF and extracts the first few words as the title.
 */
@RequiredArgsConstructor
public class FirstLineExtractor implements ArticleInfoExtractor {

    private final int maxLength;

    public Optional<ArticleInfo> extract(PDDocument document) {
        try {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setStartPage(0);
            pdfTextStripper.setEndPage(1);
            Writer writer = new BoundedStringWriter(maxLength);
            pdfTextStripper.writeText(document, writer);

            String text = writer.toString();

            if (text.isEmpty()) {
                System.out.println("No lines found in the first page of the PDF.");
                return Optional.empty();
            } else {
                ArticleInfo articleInfo = new ArticleInfo();
                articleInfo.setTitle(text);
                return Optional.of(articleInfo);
            }

        } catch (IOException e) {
            System.out.println("Error reading PDF file " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequiredArgsConstructor
    public static class BoundedStringWriter extends StringWriter {

        final int limit;
        int count = 0;

        @Override
        public void write(String str) {
            if (count < limit) {
                super.write(str);
                count += str.length();
            }
        }

    }

}
