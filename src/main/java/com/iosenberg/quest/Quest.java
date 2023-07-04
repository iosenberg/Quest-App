package com.iosenberg.quest;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public record Quest(@Id Long id, Long questlineId, String name, String description, RECURRENCE recurrence, String location, LocalDate dueDate, String reward, boolean completed) {
    public enum RECURRENCE {
        NONE,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }
}