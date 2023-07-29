package jp.livlog.gpt3sample;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

public class Sample1 {

    public static void main(final String[] args) throws Exception {

        final var config = ResourceBundle.getBundle("config");

        final var token = config.getString("openai.token");
        final var service = new OpenAiService(token, Duration.ofSeconds(60));

        System.out.println("\nCreating completion...");

        final var message = "やっぱり、冬の鍋はおいしいですね。";
        final var prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever.";

        final List <ChatMessage> messages = new ArrayList <>();
        final var promptMessage = new ChatMessage();
        promptMessage.setRole("system");
        promptMessage.setContent(prompt);
        messages.add(promptMessage);
        final var userMessage = new ChatMessage();
        userMessage.setRole("user");
        userMessage.setContent(message);
        messages.add(userMessage);

        final var request = ChatCompletionRequest.builder()
                .model("gpt-4")
                // .model("gpt-3.5-turbo")
                .messages(messages)
                .maxTokens(2048)
                .build();

        final var completionResult = service.createChatCompletion(request);

        final var choiceList = completionResult.getChoices();

        if (choiceList.isEmpty()) {
            throw new Exception("Failed to parse.");
        }

        for (final ChatCompletionChoice choice : choiceList) {
            System.out.println(choice);
        }

    }

}
