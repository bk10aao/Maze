package Utils;

import AOC2024.Maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class ReadFile {
    public static List<String> getInput(String fileName) throws IOException {
        String filePath = Objects.requireNonNull(Maze.class.getClassLoader().getResource(fileName)).getPath();
        Stream<String> lines = Files.lines(Paths.get(filePath));
        return lines.toList();
    }
}
