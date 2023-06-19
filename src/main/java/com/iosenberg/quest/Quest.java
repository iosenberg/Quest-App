package com.iosenberg.quest;

import java.time.LocalDate;

public record Quest(Long id, String name, String description, TYPE type, String location, LocalDate dueDate, Objective[] objectives, String reward, boolean completed) {
    public enum TYPE {
        NONE,
        TIMED,
        DAILY
    }

    public static class Objective {
        String name;
        boolean completed;

        Objective(String name) {
            this.name = name;
            completed = false;
        }
    }
}
