package com.iosenberg.quest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDate;

@JsonTest
class JsonTests {
    @Autowired
    private JacksonTester<Objective> oJson;

	@Autowired
	private JacksonTester<Quest> qJson;

    @Autowired
    private JacksonTester<Questline> qlJson;

    private Objective objective = new Objective(0L, 0L, (byte)0, "Objective", true);

    private String objectiveString = """
            {
                "id":0,
                "questId":0,
                "order":0,
                "name":"Objective",
                "completed":true
            }
            """;

	private Quest quest = new Quest(0L, 0L, "Quest", "This is a quest",
										Quest.RECURRENCE.NONE, "Home", LocalDate.parse("2023-06-21"),
										"A cookie!", false);

    private String questString = """
            {
                "id":0,
                "questlineId":0,
                "name":"Quest",
                "description":"This is a quest",
                "recurrence":"NONE",
                "location":"Home",
                "dueDate":"2023-06-21",
                "reward":"A cookie!",
                "completed":false
            }
            """;

    private Questline questline = new Questline(0L, "Questline");

    private String questlineString = """
            {
                "id":0,
                "name":"Questline"
            }
            """;

    @Test
    void objectiveSerializationTest() throws IOException {
        JsonContent<Objective> objectiveJson = oJson.write(objective);
        assertThat(objectiveJson).hasJsonPathNumberValue("@.id");
        assertThat(objectiveJson).extractingJsonPathNumberValue("@.id").isEqualTo(0);
        assertThat(objectiveJson).hasJsonPathNumberValue("@.questId");
        assertThat(objectiveJson).extractingJsonPathNumberValue("@.questId").isEqualTo(0);
        assertThat(objectiveJson).hasJsonPathNumberValue("@.ordering");
        assertThat(objectiveJson).extractingJsonPathNumberValue("@.ordering").isEqualTo(0);
        assertThat(objectiveJson).hasJsonPathStringValue("@.name");
        assertThat(objectiveJson).extractingJsonPathStringValue("@.name").isEqualTo("Objective");
        assertThat(objectiveJson).hasJsonPathBooleanValue("@.completed");
        assertThat(objectiveJson).extractingJsonPathBooleanValue("@.completed").isEqualTo(true);
    }

    @Test
    void objectiveDeserializationTest() throws IOException {
        Objective objectiveObject = oJson.parseObject(objectiveString);
        assertThat(objectiveObject.id()).isEqualTo(objective.id());
        assertThat(objectiveObject.questId()).isEqualTo(objective.questId());
        assertThat(objectiveObject.ordering()).isEqualTo(objective.ordering());
        assertThat(objectiveObject.name()).isEqualTo(objective.name());
        assertThat(objectiveObject.completed()).isEqualTo(objective.completed());
    }

    @Test
    void questSerializationTest() throws IOException {
        JsonContent<Quest> questJson = qJson.write(quest);
        assertThat(questJson).hasJsonPathNumberValue("@.id");
        assertThat(questJson).extractingJsonPathNumberValue("@.id").isEqualTo(0);
        assertThat(questJson).hasJsonPathNumberValue("@.questlineId");
        assertThat(questJson).extractingJsonPathNumberValue("@.questlineId").isEqualTo(0);
        assertThat(questJson).hasJsonPathStringValue("@.name");
        assertThat(questJson).extractingJsonPathStringValue("@.name").isEqualTo("Quest");
        assertThat(questJson).hasJsonPathStringValue("@.description");
        assertThat(questJson).extractingJsonPathStringValue("@.description").isEqualTo("This is a quest");
        assertThat(questJson).hasJsonPathStringValue("@.recurrence");
        assertThat(questJson).extractingJsonPathStringValue("@.recurrence").isEqualTo("NONE");
        assertThat(questJson).hasJsonPathStringValue("@.location");
        assertThat(questJson).extractingJsonPathStringValue("@.location").isEqualTo("Home");
        assertThat(questJson).hasJsonPathStringValue("@.dueDate");
        assertThat(questJson).extractingJsonPathStringValue("@.dueDate").isEqualTo("2023-06-21");
        assertThat(questJson).hasJsonPathStringValue("@.reward");
        assertThat(questJson).extractingJsonPathStringValue("@.reward").isEqualTo("A cookie!");
        assertThat(questJson).hasJsonPathBooleanValue("@.completed");
        assertThat(questJson).extractingJsonPathBooleanValue("@.completed").isEqualTo(false);
    }

    @Test
    void questDeserializationTest() throws IOException {
        Quest questObject = qJson.parseObject(questString);
        assertThat(questObject.id()).isEqualTo(quest.id());
        assertThat(questObject.questlineId()).isEqualTo(quest.questlineId());
        assertThat(questObject.name()).isEqualTo(quest.name());
        assertThat(questObject.description()).isEqualTo(quest.description());
        assertThat(questObject.recurrence()).isEqualTo(quest.recurrence());
        assertThat(questObject.location()).isEqualTo(quest.location());
        assertThat(questObject.dueDate()).isEqualTo(quest.dueDate());
        assertThat(questObject.reward()).isEqualTo(quest.reward());
        assertThat(questObject.completed()).isEqualTo(quest.completed());
    }

    @Test
    void questlineSerializationTest() throws IOException {
        JsonContent<Questline> questlineJson = qlJson.write(questline);
        assertThat(questlineJson).hasJsonPathNumberValue("@.id");
        assertThat(questlineJson).extractingJsonPathNumberValue("@.id").isEqualTo(0);
        assertThat(questlineJson).hasJsonPathStringValue("@.name");
        assertThat(questlineJson).extractingJsonPathStringValue("@.name").isEqualTo("Questline");
    }

    @Test
    void questlineDeserializationTest() throws IOException {
        Questline questlineObject = qlJson.parseObject(questlineString);
        assertThat(questlineObject.id()).isEqualTo(questline.id());
        assertThat(questlineObject.name()).isEqualTo(questline.name());
    }
}
