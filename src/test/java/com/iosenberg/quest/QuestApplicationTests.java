package com.iosenberg.quest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class QuestApplicationTests {

	@Test
	void contextLoads() {
		assertThat(1).isEqualTo(1);
	}

}

/*
@SpringBootTest
public class QuestApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

	void JUnitFuckingWorks() {
		assertThat(1).isEqualTo(2);
	}
	
	void springBootFuckingWorks() {
		assertThat(restTemplate).isNotEqualTo(null);
	}

}*/
