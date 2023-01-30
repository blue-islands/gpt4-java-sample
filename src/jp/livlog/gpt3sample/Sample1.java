package jp.livlog.gpt3sample;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

public class Sample1 {

    public static void main(final String[] args) {

        final var token = System.getenv("OPENAI_TOKEN");
        final OpenAiService service = new OpenAiService(token);

        System.out.println("\nCreating completion...");
        final var completionRequest = CompletionRequest.builder()
                .model("ada")
                .prompt("Somebody once told me the world is gonna roll me")
                .echo(true)
                .user("testing")
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }

}
