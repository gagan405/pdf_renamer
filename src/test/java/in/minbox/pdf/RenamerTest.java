package in.minbox.pdf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import in.minbox.pdf.extractors.PDFInfoExtractor;
import in.minbox.pdf.extractors.PDFTitleExtractor;
import in.minbox.pdf.naming.NamingStrategy;

import static org.junit.Assert.*;

public class RenamerTest {

    @Test
    public void renameFilesInDirectory() {
        String path = "src/test/resources";

        List<ArticleInfoExtractor> articleInfoExtractors = new ArrayList<>();
        articleInfoExtractors.add(new PDFInfoExtractor());
        articleInfoExtractors.add(new PDFTitleExtractor());

        Renamer renamer = new Renamer(new PdfNameExtractor(articleInfoExtractors), NamingStrategy.CAMEL_CASE);

        renamer.renameFilesInDirectory(path, true);
    }

    @Test
    public void testRename() {
        File file = new File("src/test/resources/p28.pdf");
        file.renameTo(new File("src/test/resources", "p29.pdf"));
    }
}