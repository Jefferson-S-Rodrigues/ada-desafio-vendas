package com.letscode.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class Sale {
    private LocalDate date;
    private String name;
    private Double value;

    @Override
    public String toString() {
        return String.format("[%s - %s: R$%.2f]", date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), name, value);
    }
}
