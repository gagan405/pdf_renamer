package in.minbox.pdf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import in.minbox.pdf.naming.NamingStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Renamer {

    private final PdfNameExtractor nameExtractor;
    private final NamingStrategy namingStrategy;

    public void renameFilesInDirectory(String dirPath, boolean recursive) {
        try {
            Path root = Paths.get(dirPath);

            Stream<Path> walk = Files.walk(Paths.get(dirPath));
            walk.filter(Files::isRegularFile)
                .filter(file -> file.getParent().equals(root))
                .map(Path::toFile)
                .filter(this::isPdfFile)
                .forEach(x -> rename(dirPath, x));

            if (recursive) {
                List<String> subDirs = Files.walk(Paths.get(dirPath))
                    .filter(Files::isDirectory)
                    .map(x -> x.toString())
                    .filter(x -> !x.equals(dirPath))
                    .collect(Collectors.toList());
                subDirs.forEach(x -> renameFilesInDirectory(x, true));
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to read files " + e.getMessage(), e);
        }
    }

    private void rename(String directory, File file) {
        Optional<ArticleInfo> articleInfo = nameExtractor.extract(file);
        articleInfo.ifPresent(info -> {
            String converted = namingStrategy.format(info.getTitle());
            boolean isSuccess = file.renameTo(new File(directory, converted + ".pdf"));
            if(!isSuccess) {
                throw new RuntimeException("Failed to rename file : " + file.getName());
            }
        });
    }

    private boolean isPdfFile(File file) {
        return file.getName().toLowerCase().endsWith(".pdf");
    }

}
