package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;


public class DeckList implements DeckDatabase {
	
	private Map<String, ArrayList<Deck>> deckMap = new HashMap<>();
	private UserList userDatabase;
	
	public DeckList(UserList userDatabase) {
		this.userDatabase = userDatabase;
	}
	
	@Override
	public void addDeck(String title, ArrayList<Flashcard> flashcards, String createdBy, boolean publicDeck) {
		// TODO Auto-generated method stub
		// !deckMap.containsKey(deck.getDeckTitle())
		// NEED TO CHECK IF DECK CREATEDBY HAS A USER WITHIN DATABASE
		
		if (userDatabase.getCurrentUser().getUsername().equals(createdBy)) {
			if (!deckMap.containsKey(title)) {
				deckMap.put(title, new ArrayList<Deck>());
			}
			
			deckMap.get(title).add(new Deck(title, flashcards, createdBy, publicDeck));
				
		} else {
			throw new IllegalArgumentException("User does not exist or isn't logged in to create that deck!");
		}
		
	}

	@Override
	public ArrayList<Deck> getDecksByTitle(String deckTitle) {
		// TODO Auto-generated method stub
		return deckMap.get(deckTitle);
	}

	@Override
	public void updateDeck(Deck deck, ArrayList<Flashcard> flashcards) {
		// TODO Auto-generated method stub
		ArrayList<Deck> getDecksByTitle = this.getDecksByTitle(deck.getDeckTitle());
		if (deckMap.get(deck.getDeckTitle()) == null) {
			throw new IllegalArgumentException("Deck does not exist");
		} else {
			if (getDecksByTitle.contains(deck) == false) {
				throw new IllegalArgumentException("Deck does not exist");
			} else {
				for (int i = 0; i < getDecksByTitle.size(); i++) {
					if (getDecksByTitle.get(i).equals(deck)) {
						getDecksByTitle.get(i).setFlashcards(flashcards);
					}
				}
			}
		}
	}

	@Override
	public void deleteDeck(Deck deck) {
		// TODO Auto-generated method stub
		ArrayList<Deck> getDecksByTitle = this.getDecksByTitle(deck.getDeckTitle());
		if (getDecksByTitle == null) {
			throw new IllegalArgumentException("Deck does not exist");
		} else {
			if (getDecksByTitle.contains(deck) == false) {
				throw new IllegalArgumentException("Deck does not exist");
			} else {
				getDecksByTitle.remove(deck);
			}
		}
	}

	@Override
	public ArrayList<Deck> getAllPublicDecks() {
		// TODO Auto-generated method stub
		ArrayList<Deck> publicDecks = new ArrayList<>();
		
		for (Map.Entry<String, ArrayList<Deck>> entry : deckMap.entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (entry.getValue().get(i).getPublicity() == true) {
					publicDecks.add(entry.getValue().get(i));
				}
			}
		}
		
		if (publicDecks.size() == 0) {
			throw new IllegalArgumentException("There are no public decks available.");
		} else {
			return publicDecks;
		}
		
	}

	@Override
	public ArrayList<Deck> getAllUserDecks(User user) {
		// TODO Auto-generated method stub
		return user.userDeckList();
	}
	
	@Override
	public ArrayList<Deck> getAllCurrentUserDecks() {
		return userDatabase.getCurrentUser().userDeckList();
	}

}
