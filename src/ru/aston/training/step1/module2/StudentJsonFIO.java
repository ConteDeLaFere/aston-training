package ru.aston.training.step1.module2;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StudentJsonFIO {

    private final JsonMapper mapper = JsonMapper.builder()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .findAndAddModules()
            .build();

    private final String filepath;

    public StudentJsonFIO(String filepath) {
        this.filepath = filepath;
    }

    public void saveStudents(List<Student> students) {
        try {
            mapper.writeValue(new File(filepath), students);
        } catch (JacksonException e) {
            System.out.println("Ошибка сохранения информации о студентах в файл");
        }
    }

    public List<Student> loadStudents() {
        File file = new File(filepath);
        if  (!file.exists()) {
            System.out.println("Файл не существует");
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Student.class));
        } catch (JacksonException e) {
            System.out.println("Ошибка при чтении информации о студентах из файла");
            return new ArrayList<>();
        }
    }
}
