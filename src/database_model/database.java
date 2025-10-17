package database_model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

//   @param <T> The type of object stored (e.g., EmployeeUser, Product, CustomerProduct)

public abstract class Database<T> {
    protected ArrayList<T> records;
    protected String filename;

    public Database(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    // ----------- ABSTRACT METHODS (implemented by subclasses) -----------
    protected abstract T createRecordFrom(String line);

    // ----------------- COMMON IMPLEMENTATION ----------------------------

    /** Reads all records from the file into memory. */
    /*public void readFromFile() throws IOException {
        records.clear();
        Path path = Paths.get(filename);
        if (Files.notExists(path))
            Files.createFile(path);

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String line : lines) {
            if (line.trim().isEmpty())
                continue;
            T obj = createRecordFrom(line.trim());
            if (obj != null)
                records.add(obj);
        }
    }*/
    public void readFromFile() {
        String line;// to read a line as a string from the file.
        records.clear();
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) // While the file has a next line (will read until no new line is found).
            {
                line = scan.nextLine();
                if (null == createRecordFrom(line)) {
                    break;
                } else {
                    records.add(createRecordFrom(line));
                }

            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Returns a reference to the internal ArrayList. */
    public ArrayList<T> returnAllRecords() {
        return records;
    }

    /** Checks if a record with the given key exists. */
    public boolean contains(String key) {
        return getRecord(key) != null;
    }

    /** Returns a record with the matching key, or null if not found. */
    public T getRecord(String key) {
        for (T record : records) {
            if (getSearchKey(record).equals(key))
                return record;
        }
        return null;
    }

    /** Inserts a record if it doesn’t already exist. */
    public void insertRecord(T record) {
        if (!contains(getSearchKey(record))) {
            records.add(record);
        }
    }

    /** Deletes a record whose key matches. */
    public void deleteRecord(String key) {
        records.removeIf(r -> getSearchKey(r).equals(key));
    }

    /** Saves all current records back to the file. */
    public void saveToFile() throws IOException {
        Path path = Paths.get(filename);
        if (Files.notExists(path))
            Files.createFile(path);

        List<String> lines = new ArrayList<>();
        for (T record : records) {
            lines.add(lineRepresentation(record));
        }

        Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
