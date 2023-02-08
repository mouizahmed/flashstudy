package Models;

import java.util.ArrayList;

public class PublicDecks {

	public ArrayList<Deck> allDecks;
	
	
	public PublicDecks(ArrayList<Deck> allDecks) {
		this.allDecks = allDecks;
	}
	
	public ArrayList<Deck> getAllDecks() {
		return allDecks;
	}
}
