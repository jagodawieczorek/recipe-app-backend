package jagodawieczorek.recipeappbackend.services;

import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final OllamaChatModel ollamaChatModel;
    private final EmbeddingStoreContentRetriever contentRetriever;

    @Override
    public String getAnswer(String question) {
        final Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(ollamaChatModel)
                .contentRetriever(contentRetriever)
                .build();

        return assistant.answer(question);
    }

    interface Assistant {
        String answer(String userMessage);
    }
}
