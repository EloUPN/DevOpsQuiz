package fr.miage.devopsquiz;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class ScoreStore {
    private final Path file;
    public ScoreStore(Path file) { this.file = file; }
    public void save(String pseudo, int score) throws IOException {
        if (pseudo == null || !pseudo.matches("[A-Za-z0-9_-]{1,24}"))
            throw new IllegalArgumentException("Pseudo: 1 à 24 caractères lettres/chiffres/_/-");
        Files.createDirectories(file.toAbsolutePath().getParent());
        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Files.writeString(file, date + ";" + pseudo + ";" + score + System.lineSeparator(),
            StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
    public List<String> bestScores() throws IOException {
        if (!Files.exists(file)) return List.of();
        List<String> lines = new ArrayList<>(Files.readAllLines(file, StandardCharsets.UTF_8));
        lines.removeIf(s -> !s.matches("[^;]+;[^;]+;[0-9]+"));
        lines.sort((a,b) -> Integer.compare(Integer.parseInt(b.substring(b.lastIndexOf(';') + 1)),
                                               Integer.parseInt(a.substring(a.lastIndexOf(';') + 1))));
        return lines.subList(0, Math.min(5, lines.size()));
    }
}
