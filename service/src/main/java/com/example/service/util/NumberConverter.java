package com.example.service.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Converter
public class NumberConverter implements AttributeConverter<String, String> {


    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(String s) {
        return Secret.encrypt(s);
    }

    @SneakyThrows
    @Override
    public String convertToEntityAttribute(String s) {
        return Secret.decrypt(s);
    }
}
