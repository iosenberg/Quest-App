package com.iosenberg.quest;

import java.sql.Date;

import org.springframework.data.annotation.Id;

public record Quest(@Id Long id, Long questlineId, String name, String description, RECURRENCE recurrence, String location, Date dueDate, String reward, boolean completed) {
    public enum RECURRENCE {
        NONE,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }
}