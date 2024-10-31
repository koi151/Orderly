package com.koi151.QTDL.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.koi151.QTDL.customExceptions.InvalidEnumValueException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum OrderStatusEnum {
    processing("Processing"),
    delivering("Delivering"),
    canceled("Canceled"),
    received("Received");

    private final String day;

    private static final OrderStatusEnum[] VALUES = values();

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static OrderStatusEnum fromString(String s) {
        return s == null ? null
            : Arrays.stream(VALUES)
            .filter(value -> value.name().equals(s.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new InvalidEnumValueException("Giá trị enum không hợp lệ: " + s));
    }
}
