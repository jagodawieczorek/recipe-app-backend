package jagodawieczorek.recipeappbackend.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {
    @Value("${embedding.model.name}")
    private String embeddingModelName;
    @Value("${ollama.base-url}")
    private String ollamaBaseUrl;

    @Value("${ollama.base-url}")
    private String baseUrl;
    @Value("${ollama.model-name}")
    private String modelName;

    @Value("${qdrant.host}")
    private String qdrantHost;
    @Value("${qdrant.grpc-port}")
    private int qdrantGrpcPort;
    @Value("${qdrant.collection.name}")
    private String qdrantCollectionName;

    @Bean
    public OllamaChatModel getOllamaChatModel() {
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .logRequests(true)
                .build();
    }

    @Bean
    public EmbeddingModel getEmbeddingModel() {
        return OllamaEmbeddingModel.builder()
                .baseUrl(ollamaBaseUrl)
                .modelName(embeddingModelName)
                .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return QdrantEmbeddingStore.builder()
                .host(qdrantHost)
                .port(qdrantGrpcPort)
                .collectionName(qdrantCollectionName)
                .build();
    }

    @Bean
    public EmbeddingStoreContentRetriever retriever(final EmbeddingStore<TextSegment> embeddingStore,
                                                    final EmbeddingModel embeddingModel) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(1)
                .minScore(0.75)
                .build();
    }
}
