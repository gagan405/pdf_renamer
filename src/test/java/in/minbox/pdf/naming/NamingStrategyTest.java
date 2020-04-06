package in.minbox.pdf.naming;

import org.junit.Assert;
import org.junit.Test;

public class NamingStrategyTest {

    @Test
    public void testCamelCase() {
        String input = "Some PDF title which needs To Be in Camel    case";
        String output = NamingStrategy.CAMEL_CASE.format(input);
        Assert.assertEquals("SomePdfTitleWhichNeedsToBeInCamelCase", output);
    }

    @Test
    public void testSnakeCase() {
        String input = "Some PDF title which needs To Be in Camel    case";
        String output = NamingStrategy.SNAKE_CASE.format(input);
        Assert.assertEquals("some_pdf_title_which_needs_to_be_in_camel_case", output);
    }

    @Test
    public void testHyphenated() {
        String input = "Some PDF title which needs To Be in Camel    case";
        String output = NamingStrategy.HYPHENATED.format(input);
        Assert.assertEquals("some-pdf-title-which-needs-to-be-in-camel-case", output);
    }
}
