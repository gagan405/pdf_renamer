package in.minbox.pdf;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Ignore;
import org.junit.Test;

import in.minbox.pdf.extractors.PDFInfoExtractor;

@Ignore
public class ReaderTest {

    @Test
    public void extractHeader() throws Exception{
        String path = "src/test/resources/pivot-tracing.pdf";
        File file = new File(path);
        System.out.println(file.exists());
        PDDocument document = PDDocument.load(file);
        new PDFInfoExtractor(100).extract(document);
    }
}
