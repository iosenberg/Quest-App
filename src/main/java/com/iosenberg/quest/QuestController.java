package com.iosenberg.quest;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/quests")
public class QuestController {
    private QuestRepository questRepository;
    
    public QuestController(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<Quest> findById(@PathVariable Long requestedId) {
        Optional<Quest> questOptional = questRepository.findById(requestedId);
        if(questOptional.isPresent()) {
            return ResponseEntity.ok(questOptional.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createQuest(@RequestBody Quest newQuestRequest, UriComponentsBuilder ucb) {
        Quest savedQuest = questRepository.save(newQuestRequest);
        System.out.println("BING BONG " + savedQuest.id() + ":" + savedQuest.name());
        URI locationOfNewQuest = ucb
            .path("quests/{id}")
            .buildAndExpand(savedQuest.id())
            .toUri();
        return ResponseEntity.created(locationOfNewQuest).build();
    }
}
