package com.letscode.service;

import com.letscode.model.Sale;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CSVService {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static List<Sale> read_csv(String file) throws IOException {
        return Files.readAllLines(Paths.get(file)).stream().skip(1)
                .map(s -> {
                    LocalDate ld = null;
                    String name = null;
                    Double value = null;
                    List<String> d = Arrays.asList(s.split(","));
                    if (d.size() == 3) {
                        Optional<String> hasDate = Optional.of(d.get(0)).filter(str -> str.matches("\\d{2}/\\d{2}/\\d{4}"));
                        ld = hasDate.isPresent() ? LocalDate.parse(d.get(0), formatter) : null;
                        name = Optional.ofNullable(d.get(1)).orElse(null);
                        Optional<String> hasNumber = Optional.of(d.get(2)).filter(str -> str.matches("[0-9]+."));
                        value = hasNumber.isPresent() ? hasNumber.map(Double::parseDouble).get() : null;
                    }
                    return new Sale(ld, name, value);
                }).collect(Collectors.toList());
    }
}
