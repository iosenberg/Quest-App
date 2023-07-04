package com.iosenberg.quest;

import org.springframework.data.annotation.Id;

public record Objective(@Id Long id, Long questId, byte ordering, String name, boolean completed) {
}
