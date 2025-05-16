package jagodawieczorek.recipeappbackend.controllers;

import jagodawieczorek.recipeappbackend.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatController {

    public final ChatService chatService;

    @PostMapping("/ask")
    public String ask(@RequestBody String question) {
        return chatService.getAnswer(question);
    }
}
