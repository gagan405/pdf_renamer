package in.minbox.pdf;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import in.minbox.pdf.extractors.PDFInfoExtractor;

public class ReaderTest {

    @Test
    public void extractHeader() {
        String path = "src/test/resources/p28.pdf";
        Path paths = Paths.get(path);
        File file = new File(path);
        System.out.println(file.exists());
        new PDFInfoExtractor().extractHeader(file.getAbsolutePath());
    }
}
