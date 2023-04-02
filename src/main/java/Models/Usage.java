package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usage {
	@JsonProperty("prompt_tokens")
	private int prompt_tokens;
	
	@JsonProperty("completion_tokens")
	private int completion_tokens;
	
	@JsonProperty("total_tokens")
	private int total_tokens;
	
	public int getPrompt_Tokens() {
		return prompt_tokens;
	}
	
	public void setPrompt_Tokens(int prompt_tokens) {
		this.prompt_tokens = prompt_tokens;
	}
	
	public int getCompletion_Tokens() {
		return completion_tokens;
	}
	
	public void setCompletion_Tokens(int completion_tokens) {
		this.completion_tokens = completion_tokens;
	}
	
	public int getTotalTokens() {
		return total_tokens;
	}
	
	public void setTotal_Tokens(int total_tokens) {
		this.total_tokens = total_tokens;
	}
}
