package in.minbox.pdf;

import java.util.ArrayList;
import java.util.List;

import in.minbox.pdf.extractors.ExtractorType;
import in.minbox.pdf.extractors.FirstLineExtractor;
import in.minbox.pdf.extractors.PDFInfoExtractor;

public final class PDFNameExtractorFactory {

    private PDFNameExtractorFactory() {}

    public static PdfNameExtractor getExtractor(int maxNameLength, ExtractorType type) {
        List<ArticleInfoExtractor> articleInfoExtractors = new ArrayList<>();
        switch (type) {
            case FIRST_LINE:
                articleInfoExtractors.add(new FirstLineExtractor(maxNameLength));
                break;
            case PDF_INFO:
                articleInfoExtractors.add(new PDFInfoExtractor(maxNameLength));
                break;
            case DEFAULT:
                articleInfoExtractors.add(new PDFInfoExtractor(maxNameLength));
                articleInfoExtractors.add(new FirstLineExtractor(maxNameLength));
                break;
        }

        return new PdfNameExtractor(articleInfoExtractors);
    }

}
