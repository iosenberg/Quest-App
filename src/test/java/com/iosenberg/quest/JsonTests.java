package com.iosenberg.quest;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class JsonTests {
    @Autowired
    private JacksonTester<Quest> json;

    @Test
    void questSerializationTest() throws IOException {
        Quest quest = new Quest(0L, "Quest", "This is a quest", Quest.TYPE.NONE, "", LocalDate.now(), new Quest.Objective[] {new Quest.Objective("BingBong!")}, "A cookie!", false);

        // System.out.println(json.write(quest));
    }

    @Test
    void questDeserializationTest() throws IOException {

    }

    @Test
    void questlineSerializationTest() throws IOException {

    }

    @Test
    void questlineDeserializationTest() throws IOException {

    }
}
