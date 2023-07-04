package com.iosenberg.quest;

import org.springframework.data.annotation.Id;

public record Questline(@Id Long id, String name) {
}
