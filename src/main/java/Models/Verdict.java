package Models;

public class Verdict {
	
	private Boolean verdict;
	private String correction;
	
	public Verdict(Boolean verdict) {
		this.verdict = verdict;
	}
	
	public Verdict(Boolean verdict, String correction) {
		this.verdict = verdict;
		this.correction = correction;
	}
	
	public Boolean getVerdict() {
		return this.verdict;
	}
	
	public void setVerdict(Boolean verdict) {
		this.verdict = verdict;
	}
	
	public String getCorrection() {
		return this.correction;
	}
	
	public void setCorrection(String correction) {
		this.correction = correction;
	}

}
