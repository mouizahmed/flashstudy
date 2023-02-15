package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;


public class DeckList implements DeckDatabase {
	
	private HashMap<String, ArrayList<Deck>> deckMap = new HashMap<>();
	private UserList userDatabase;
	
	public DeckList(UserList userDatabase) {
		this.userDatabase = userDatabase;
	}
	
	
	@Override
	public Deck createDeck(String title, String createdBy) {
		
		if (!userDatabase.getCurrentUser().getUsername().equals(createdBy)) {
			throw new IllegalArgumentException("User is either not signed in or doesn't exist.");
		} else {
			return new Deck(title, createdBy);
		}
	}
	
	@Override
	public void addDeck(String title, ArrayList<Flashcard> flashcards, String createdBy, boolean publickDeck) {
		// TODO Auto-generated method stub
		// !deckMap.containsKey(deck.getDeckTitle())
		// NEED TO CHECK IF DECK CREATEDBY HAS A USER WITHIN DATABASE
		
		if (userDatabase.getCurrentUser().getUsername().equals(createdBy)) {
			if (!deckMap.containsKey(title)) {
				deckMap.put(title, new ArrayList<Deck>());
			}
			
			deckMap.get(title).add(new Deck(createdBy, title));
			
			
		} else {
			throw new IllegalArgumentException("User does not exist or isn't logged in to create that deck!");
		}
		
		ArrayList<Deck> decks = deckMap.get(title);
		
	}

	@Override
	public ArrayList<Deck> getDecksByTitle(String deckTitle) {
		// TODO Auto-generated method stub
		return deckMap.get(deckTitle);
	}

	@Override
	public void updateDeck(String deckTitle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDeck(String deckTitle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Deck> getAllPublicDecks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Deck> getAllUserDecks(User currentUser) {
		// TODO Auto-generated method stub
		return null;
	}
	
	Deck binarySearch(ArrayList<Deck> decks, String deckTitle) {
		int left = 0;
		int right = decks.size()- 1;
		
		while (left <= right) {
			int mid = left + (right - left) / 2;
			
			if (decks.get(mid).getDeckTitle() == deckTitle) {
				return decks.get(mid);
			} else {
				right = mid-1;
			}
		}
		return null;
	}

}
