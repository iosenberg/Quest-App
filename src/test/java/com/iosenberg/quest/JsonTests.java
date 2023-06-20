package com.iosenberg.quest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDate;

@JsonTest
class JsonTests {
	@Autowired
	private JacksonTester<Quest> json;

    @Test
    void questSerializationTest() throws IOException {
        Quest quest = new Quest(0L, "Quest", "This is a quest", Quest.TYPE.NONE, "", LocalDate.now(), new Quest.Objective[] {new Quest.Objective("BingBong!", false)}, "A cookie!", false);
		assertThat(1).isEqualTo(json.write(quest));
		assertThat(json.write(quest)).hasJsonPathNumberValue("@.id");
        // System.out.println(json.write(quest).toString());
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
