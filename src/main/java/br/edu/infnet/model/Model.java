package br.edu.infnet.model;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

public abstract class Model<T> {
    protected String csvFileName;
    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Model(String csvFileName) {
        this.csvFileName = csvFileName;
        this.createdAt = LocalDateTime.now();
    }

    public void save(T object) {
        try {
            File file = new File(csvFileName);

            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            boolean isNewFile = !file.exists() || file.length() == 0;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                if (isNewFile) {
                    writer.write(createCsvHeader());
                    writer.newLine();
                }

                if (this.id == null) {
                    this.id = generateNextId();
                }
                this.updatedAt = LocalDateTime.now();

                writer.write(toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar no arquivo CSV: " + e.getMessage(), e);
        }
    }

    public T findById(Long id) {
        File file = new File(csvFileName);
        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                T object = fromCsv(line);
                if (getObjectId(object).equals(id)) {
                    return object;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo CSV: " + e.getMessage(), e);
        }
        return null;
    }

    public List<T> findAll() {
        List<T> results = new ArrayList<>();
        File file = new File(csvFileName);

        if (!file.exists()) {
            return results;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                results.add(fromCsv(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo CSV: " + e.getMessage(), e);
        }
        return results;
    }

    private String createCsvHeader() {
        List<String> headers = new ArrayList<>();

        Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
        for (Field field : superFields) {
            if (!field.getName().equals("csvFileName")) {
                headers.add(field.getName());
            }
        }

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers()) &&
                    !java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
                headers.add(field.getName());
            }
        }

        return String.join(";", headers);
    }

    private Long generateNextId() {
        List<T> all = findAll();
        if (all.isEmpty()) {
            return 1L;
        }
        return all.stream()
                .mapToLong(this::getObjectId)
                .max()
                .orElse(0L) + 1;
    }

    public abstract String toCsv();

    public abstract T fromCsv(String csvLine);

    protected abstract Long getObjectId(T object);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
