package com.koi151.QTDL.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.koi151.QTDL.customExceptions.InvalidEnumValueException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TrangThaiDatHangEnum {
    dangXuLy("Đang xử lý"),
    dangGiao("Đang giao"),
    daHuy("Đã hủy"),
    daNhan("Đã nhận");

    private final String day;

    private static final TrangThaiDatHangEnum[] VALUES = values();

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TrangThaiDatHangEnum fromString(String s) {
        return s == null ? null
            : Arrays.stream(VALUES)
            .filter(value -> value.name().equals(s.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new InvalidEnumValueException("Invalid DaysPosted enum value: " + s));
    }
}
