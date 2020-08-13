package ru.job4j.forum.utils;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateUtilsTest {

    @Test
    public void simpleTest() {
        LocalDateTime input = LocalDateTime.of(
                LocalDate.of(2020, 12, 15),
                LocalTime.of(10, 10)
        );
        String expect = "15.12.2020, 10:10";
        assertEquals(expect, DateUtils.parse(input));
    }

}