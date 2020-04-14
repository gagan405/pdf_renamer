package in.minbox.pdf;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import in.minbox.pdf.extractors.ExtractorType;
import in.minbox.pdf.naming.NamingStrategy;

public class Cli {

    @Parameter(names = {"--directory", "-d"},
        required = true,
        description = "Absolute path to the directory")
    private String directory;

    @Parameter(names = {"--recursive", "-r"},
        required = false,
        description = "Should the renaming be run recursively to all sub directories?")
    private boolean recursive = false;

    @Parameter(names = {"--naming-strategy", "-n"},
        required = true,
        description = "Naming strategy to use: SNAKE_CASE, HYPHENATED, CAMEL_CASE")
    private NamingStrategy namingStrategy;

    @Parameter(names = {"--max-name-length", "-l"},
        required = false,
        description = "Max length of the file name. Default 100.")
    private Integer length;

    @Parameter(names = {"--extractor", "-e"},
        required = false,
        description = "Name extractor to use: InfoExtractor, FirstLineExtractor."
            + "Default behavior uses both extractors in the order : Info -> FirstLine")
    private String nameExtractor;

    @Parameter(names = "--help", help = true)
    private boolean help = false;

    public static void main(String... args) {
        Cli cli = new Cli();
        JCommander jCommander = JCommander.newBuilder()
            .addObject(cli)
            .args(args)
            .build();

        if (cli.help) {
            jCommander.usage();
            return;
        }
        cli.doProcess();
    }

    private void doProcess() {
        ExtractorType extractorType = nameExtractor == null ? ExtractorType.DEFAULT : ExtractorType.valueOf(nameExtractor);
        int maxLength = length == null ? 100 : length;

        PdfNameExtractor nameExtractor = PDFNameExtractorFactory.getExtractor(maxLength, extractorType);
        Renamer renamer = new Renamer(nameExtractor, namingStrategy);

        renamer.renameFilesInDirectory(directory, recursive);
    }


}
