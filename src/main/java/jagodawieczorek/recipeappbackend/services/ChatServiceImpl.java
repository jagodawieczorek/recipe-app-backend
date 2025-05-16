package jagodawieczorek.recipeappbackend.services;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.ollama.OllamaChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final OllamaChatModel ollamaChatModel;

    @Override
    public String getAnswer(String question) {
        final ChatResponse chatResponse = ollamaChatModel.chat(
                ChatRequest.builder().messages(UserMessage.userMessage(question)).build()
        );
        return chatResponse.aiMessage().text();
    }
}
