package Models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;

public class AIModel {

	private HttpRequest request;
	private HttpClient client;
	private ObjectMapper mapper;
	private Dotenv dotenv;
	public AIModel() {
		mapper = new ObjectMapper();
		dotenv = Dotenv.configure().load();
	}
	
	public MultipleChoiceQuestion generateMC(String term, String definition) throws IOException, InterruptedException {
		
		String input = """
				{
					"model": "text-davinci-003",
					"prompt": "Please create a simple multiple-choice question using the following term and answer: [TERM]: [USER ANSWER]. \\nYour response should have the following format: Question: [TERM] [QUESTION TEXT] Options: A) [USER ANSWER] B) [OPTION B] C) [OPTION C] D) [OPTION D]. The answer provided should be randomly assigned to options A, B, C, or D. One of the options must be the answer provided by the user and the correct answer must be the answer provided. I want the answer to always be one of the options at face value. The other 3 options must only be variations of the answer provided. You can replace [TERM] and [ANSWER] with the actual term and answer provided by the user. Term: [%s] Answer: %s\\n",
					"temperature": 0.7,
					"max_tokens": 256,
					"top_p": 1,
					"frequency_penalty": 0,
					"presence_penalty": 0
				}
				""";
		
		input = String.format(input, term, definition);
		
		HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer " + dotenv.get("OPENAI_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();
	 
		HttpClient client = HttpClient.newHttpClient();
		
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 
		 GPTResponse response1 = mapper.readValue(response.body(), GPTResponse.class);
		 
		 String choices = response1.getChoices().get(0).getText();
        choices = choices.replaceAll("\\n", "");
        
        System.out.println(choices);
        
        String question = choices.split("Question: ")[1].split("Options: ")[0].trim();
        System.out.println(question);
	        
        String optionsString = choices.split("Options:")[1];
        System.out.println(optionsString);
        
        String[] options = optionsString.split("[A-D]\\) ");
        
        for (int i = 0; i < options.length; i++) {
            options[i] = options[i].trim();
        }
        
//        for (String option: options) {
//        	System.out.println(option);
//        }
        
     
        
        MultipleChoiceQuestion mcQ = new MultipleChoiceQuestion(question, options[1], Arrays.copyOfRange(options, 1, 5));
	        
	        
	        
		return mcQ;
		
	}
	
	public FillInTheBlankQuestion generateFill(String term, String definition) {
		return null;
		
	}
	
	public TrueFalseQuestion generateBool(String term, String definition) {
		return null;
		
	}
	
	public Verdict verifyFlashcard(String term, String definition) throws IOException, InterruptedException {
		
		String input = """
				{
					"model": "text-davinci-003",
					"prompt": "Verify Term Definition\\n\\nGiven a term and its definition, please verify whether the definition is correct. If the definition is incorrect, please provide the correct definition. \\n\\nTerm: [%s]\\nDefinition: [%s]\\n\\nIs the above definition correct? If yes, respond with Verdict: True If no, respond with Verdict: False Correct Definition: [CORRECT DEFINITION]. Note: if the provided definition has a similar meaning, consider it true.\\n\\n",
					"temperature": 0.7,
					"max_tokens": 256,
					"top_p": 1,
					"frequency_penalty": 0,
					"presence_penalty": 0
				}
				""";
			
		input = String.format(input, term, definition);
		
		HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create("https://api.openai.com/v1/completions"))
	            .header("Content-Type", "application/json")
	            .header("Authorization","Bearer " + dotenv.get("OPENAI_KEY"))
	            .POST(HttpRequest.BodyPublishers.ofString(input))
	            .build();
	 
		HttpClient client = HttpClient.newHttpClient();
		
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 
		GPTResponse response1 = mapper.readValue(response.body(), GPTResponse.class);

		Verdict finalVerdict = null;
			 
		 String choices = response1.getChoices().get(0).getText();
		 choices = choices.replaceAll("\\n", "");

		 String verdict = choices.split("Verdict: ")[1].split("Correct Definition: ")[0].trim();

		 
		 if (!verdict.equals("True")) {
			 String correctDefinition = choices.split("Correct Definition: ")[1];
			 System.out.println(correctDefinition);
			 finalVerdict = new Verdict(false, correctDefinition);
			 
		 } else {
			 finalVerdict = new Verdict(true);
		 }
	    return finalVerdict;
	}
	
	public String autoComplete(String term, String context) throws IOException, InterruptedException {
		
		String input = """
				{
					"model": "text-davinci-003",
					"prompt": "Context: %s\\nAutocomplete Flash Card Term: %s\\n*Note: Keep the definition short.\\n\\nDefinition:",
					"temperature": 0.7,
					"max_tokens": 256,
					"top_p": 1,
					"frequency_penalty": 0,
					"presence_penalty": 0
				}
				""";
			
		input = String.format(input, context, term);
		
		HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create("https://api.openai.com/v1/completions"))
	            .header("Content-Type", "application/json")
	            .header("Authorization","Bearer " + dotenv.get("OPENAI_KEY"))
	            .POST(HttpRequest.BodyPublishers.ofString(input))
	            .build();
	 
		HttpClient client = HttpClient.newHttpClient();
		
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 
		GPTResponse response1 = mapper.readValue(response.body(), GPTResponse.class);
	
	
			 
		 String choices = response1.getChoices().get(0).getText();
		 System.out.println(choices);
		 choices = choices.replaceAll("\\n", "").trim();
		
		 System.out.println(choices);
		return choices;
	}
	
	
}
