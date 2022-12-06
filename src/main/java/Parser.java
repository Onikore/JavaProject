package main.java;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {
    private final String path;
    private final List<Record> records = new ArrayList<>();
    private final HashSet<String> genres = new HashSet<>();
    private final HashSet<String> publishers = new HashSet<>();
    private final HashSet<String> names = new HashSet<>();
    private final HashSet<String> years = new HashSet<>();
    private final HashSet<String> platforms = new HashSet<>();

    public Parser(String path) {
        this.path = path;
    }

    public void loadStaffFromFile() {
        var file = Paths.get(path);
        if (!Files.exists(file)) throw new IllegalArgumentException();
        try {
            var lines = Files.readAllLines(file);
            lines.remove(0);
            for (var line : lines) {
                var row = line.split(",");
                if (row.length != 11) {
                    continue;
                }
                records.add(
                        new Record(
                                Integer.parseInt(row[0]), row[1], row[2], row[3],
                                row[4], row[5], Double.parseDouble(row[6]),
                                Double.parseDouble(row[7]), Double.parseDouble(row[8]), Double.parseDouble(row[9]),
                                Double.parseDouble(row[10])
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateFields() {
        for (var r : records) {
            genres.add(r.genre);
            publishers.add(r.publisher);
            names.add(r.name);
            years.add(r.year);
            platforms.add(r.platform);
        }
    }

    public List<Record> getRecords() {
        return records;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public Set<String> getPublishers() {
        return publishers;
    }

    public Set<String> getNames() {
        return names;
    }

    public Set<String> getYears() {
        return years;
    }

    public Set<String> getPlatforms() {
        return platforms;
    }
}
