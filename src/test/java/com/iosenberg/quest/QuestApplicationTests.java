package com.iosenberg.quest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestApplicationTests {

	@Autowired
    TestRestTemplate restTemplate;

    @Test
    void dataBaseTest() {
        //Errors if database is set up incorrectly
    }

    @Test
    void shouldReturnAQuestWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/quests/0", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(0);

        Double name = documentContext.read("$.name");
        assertThat(name).isEqualTo("Quest1");
    }

    // @Test
    void shouldNotReturnAQuestWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/quests/1000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    // @Test
    @DirtiesContext
    void shouldCreateANewQuest() {
	Quest newQuest = new Quest(1L, null, "Quest2", "This is NOT a quest", Quest.RECURRENCE.NONE, "Library", LocalDate.ofEpochDay(1687219200), "A gun.", false);
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/quests", newQuest, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewQuest = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewQuest, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        Double name = documentContext.read("$.name");

        assertThat(id).isNotNull();
        assertThat(name).isEqualTo("Quest2");
    }

    // @Test
    void shouldReturnAllQuestsWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/quests", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int questCount = documentContext.read("$.length()");
        assertThat(questCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(0,1);

        JSONArray names = documentContext.read("$..name");
        assertThat(names).containsExactlyInAnyOrder("Quest1", "Quest2");
    }

    // @Test
    void shouldReturnAPageOfQuests() {
        ResponseEntity<String> response = restTemplate.getForEntity("/quests?page=0&size=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }

    // @Test
    void shouldReturnASortedPageOfQuests() {
        ResponseEntity<String> response = restTemplate.getForEntity("/quests?page=0&size=1&sort=name,desc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        double name = documentContext.read("$[0].name");
        assertThat(name).isEqualTo("Quest1");
    }

    // @Test
    void shouldReturnASortedPageOfQuestsWithNoParametersAndUseDefaultValues() {
        ResponseEntity<String> response = restTemplate.getForEntity("/quests", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(2);

        JSONArray names = documentContext.read("$..name");
        assertThat(names).containsExactly("Quest1", "Quest2");
    }

    // @Test
    @DirtiesContext
    void shouldUpdateAnExistingQuest() {
		Quest questUpdate = new Quest(0L, 0L, "New Quest Name!", "This is a quest", Quest.RECURRENCE.NONE, "Home", LocalDate.parse("2023-06-21"), "A cookie!", false);
        HttpEntity<Quest> request = new HttpEntity<>(questUpdate);
        ResponseEntity<Void> response = restTemplate.exchange("/quests/0", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate.getForEntity("/quests/0", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        Double name = documentContext.read("$.name");
        assertThat(id).isEqualTo(0);
        assertThat(name).isEqualTo("New Quest Name!");
    }

    // @Test
    void shouldNotUpdateAQuestThatDoesNotExist() {
		Quest unknownQuest = new Quest(4L, 0L, "New quest name!", "This is a quest", Quest.RECURRENCE.NONE, "Home", LocalDate.parse("2023-06-21"), "A cookie!", false);
        HttpEntity<Quest> request = new HttpEntity<>(unknownQuest);
        ResponseEntity<Void> response = restTemplate.exchange("/quests/4", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

	// @Test
    @DirtiesContext
	void shouldDeleteAQuest() {
        ResponseEntity<Void> response = restTemplate.exchange("/quests/0", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate.getForEntity("/quests/0", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	// @Test
	void shouldNotDeleteAQuestThatDoesNotExist() {
        ResponseEntity<Void> deleteResponse = restTemplate.exchange("/cashcards/99999", HttpMethod.DELETE, null, Void.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}
