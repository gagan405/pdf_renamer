package in.minbox.pdf.naming;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum NamingStrategy implements NameFormatter {
    SNAKE_CASE {
        @Override
        public String format(String input) {
            return input.toLowerCase()
                .replaceAll("\\s+", "_")
                .replaceAll("[^a-zA-Z0-9_-]", "");
        }
    },
    HYPHENATED {
        @Override
        public String format(String input) {
            return input.toLowerCase()
                .replaceAll("\\s+", "-")
                .replaceAll("[^a-zA-Z0-9_-]", "");
        }
    },
    CAMEL_CASE {
        @Override
        public String format(String input) {
            return Arrays.stream(input.split("\\s+"))
                .map(x -> Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase())
                .collect(Collectors.joining())
                .replaceAll("[^a-zA-Z0-9_-]", "");
        }
    };


}
