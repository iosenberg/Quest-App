package com.iosenberg.quest;

import org.assertj.core.api.ObjectAssert;
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
	private JacksonTester<Quest> json;

	private Quest quest1Quest = new Quest(0L, "Quest1", "This is a quest",
										Quest.TYPE.NONE, "Home", LocalDate.parse("2023-06-21"),
										new Quest.Objective[] {
											new Quest.Objective("Objective1", false),
											new Quest.Objective("Objective2", false)
										},
										"A cookie!", false);
	private Quest quest2Quest = new Quest(1L, "Quest2", "This is NOT a quest",
										Quest.TYPE.TIMED, "Library", LocalDate.ofEpochDay(1687219200),
										new Quest.Objective[] {}, "A gun.", false);

    private String quest1String = """
            {
                "id":0,
                "name":"Quest1",
                "description":"This is a quest",
                "type":"NONE",
                "location":"Home",
                "dueDate":"2023-06-21",
                "objectives":[
                    {"name":"Objective1","completed":false},
                    {"name":"Objective2","completed":false}
                    ],
                "reward":"A cookie!",
                "completed":false
            }
            """;

    private String quest2String = """
            {
                "id":1,
                "name":"Quest2",
                "description":"This is not a quest",
                "type":"TIMED",
                "location":"Library",
                "dueDate":"2023-06-21",
                "objectives":[],
                "reward":"A gun.",
                "completed":false
            }
            """;

    @Test
    void questSerializationTest() throws IOException {
        JsonContent<Quest> quest1Json = json.write(quest1Quest);
        assertThat(quest1Json).hasJsonPathNumberValue("@.id");
        assertThat(quest1Json).extractingJsonPathNumberValue("@.id").isEqualTo(0);
        assertThat(quest1Json).hasJsonPathStringValue("@.name");
        assertThat(quest1Json).extractingJsonPathStringValue("@.name").isEqualTo("Quest1");
        assertThat(quest1Json).hasJsonPathStringValue("@.description");
        assertThat(quest1Json).extractingJsonPathStringValue("@.description").isEqualTo("This is a quest");
        assertThat(quest1Json).hasJsonPathStringValue("@.type");
        assertThat(quest1Json).extractingJsonPathStringValue("@.type").isEqualTo("NONE");
        assertThat(quest1Json).hasJsonPathStringValue("@.location");
        assertThat(quest1Json).extractingJsonPathStringValue("@.location").isEqualTo("Home");
        assertThat(quest1Json).hasJsonPathStringValue("@.dueDate");
        assertThat(quest1Json).extractingJsonPathStringValue("@.dueDate").isEqualTo("2023-06-21");
        assertThat(quest1Json).hasJsonPathArrayValue("@.objectives");
        ObjectAssert<Object> objectAssert = assertThat(quest1Json).extractingJsonPathArrayValue("@.objectives").first();
            objectAssert.extracting("name").isEqualTo("Objective1");
            objectAssert.extracting("completed").isEqualTo(false);
        assertThat(quest1Json).hasJsonPathStringValue("@.reward");
        assertThat(quest1Json).extractingJsonPathStringValue("@.reward").isEqualTo("A cookie!");
        assertThat(quest1Json).hasJsonPathBooleanValue("@.completed");
        assertThat(quest1Json).extractingJsonPathBooleanValue("@.completed").isEqualTo(false);
    }

    @Test
    void questDeserializationTest() throws IOException {
        //assertThat(json.parse(quest1String)).isEqualTo(quest1Quest);
        Quest questObject = json.parseObject(quest1String);
        assertThat(questObject.id()).isEqualTo(quest1Quest.id());
        assertThat(questObject.name()).isEqualTo(quest1Quest.name());
        assertThat(questObject.description()).isEqualTo(quest1Quest.description());
        assertThat(questObject.type()).isEqualTo(quest1Quest.type());
        assertThat(questObject.location()).isEqualTo(quest1Quest.location());
        assertThat(questObject.dueDate()).isEqualTo(quest1Quest.dueDate());
        Quest.Objective[] objectives = questObject.objectives();
        for(int i = 0; i < objectives.length; i++) {
            assertThat(objectives[i].name()).isEqualTo(quest1Quest.objectives()[i].name());
            assertThat(objectives[i].completed()).isEqualTo(quest1Quest.objectives()[i].completed());
        }
        assertThat(questObject.reward()).isEqualTo(quest1Quest.reward());
        assertThat(questObject.completed()).isEqualTo(quest1Quest.completed());
    }

    @Test
    void questlineSerializationTest() throws IOException {

    }

    @Test
    void questlineDeserializationTest() throws IOException {

    }
}
