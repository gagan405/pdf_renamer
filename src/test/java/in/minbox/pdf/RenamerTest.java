package in.minbox.pdf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import in.minbox.pdf.extractors.PDFInfoExtractor;
import in.minbox.pdf.extractors.FirstLineExtractor;
import in.minbox.pdf.naming.NamingStrategy;

/**
 * Tests ignored..
 * TODO.. write code to revert the names of the pdf files to original ones so that tests can be enabled
 */
@Ignore
public class RenamerTest {

    @Test
    public void renameFilesInDirectory() {
        String path = "/Users/mishraga/Documents/papers_bkup";

        List<ArticleInfoExtractor> articleInfoExtractors = new ArrayList<>();
        articleInfoExtractors.add(new PDFInfoExtractor(100));
        articleInfoExtractors.add(new FirstLineExtractor(100));

        Renamer renamer = new Renamer(new PdfNameExtractor(articleInfoExtractors), NamingStrategy.CAMEL_CASE);

        renamer.renameFilesInDirectory(path, true);
    }

    @Test
    public void testRename() {
        File file = new File("src/test/resources/p28.pdf");
        file.renameTo(new File("src/test/resources", "p29.pdf"));
    }
}