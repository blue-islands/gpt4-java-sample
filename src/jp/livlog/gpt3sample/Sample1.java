package jp.livlog.gpt3sample;

import java.util.ResourceBundle;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;

public class Sample1 {

    public static void main(final String[] args) {

        final var config = ResourceBundle.getBundle("config");

        final var token = config.getString("openai.token");
        final var service = new OpenAiService(token);

        System.out.println("\nCreating completion...");

        final var message = "ラーメンは健康にいい食べ物ですよね。";
        final var prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever.\nHuman: " + message
                + "\nAI: ";

        final var completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(prompt)
                .maxTokens(256)
                .build();
        final var completionResult = service.createCompletion(completionRequest);
        final var choiceList = completionResult.getChoices();

        for (final CompletionChoice choice : choiceList) {
            System.out.println(choice);
        }

    }

}
