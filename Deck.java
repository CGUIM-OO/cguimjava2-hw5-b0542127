import java.util.ArrayList; 
import java.util.Random; 

public class Deck {
	
	private ArrayList<Card> cards;   
	private ArrayList<Card> usedCards;
	private ArrayList<Card> openCards;
	public int nUsed;
	public Deck(int nDeck){ 
		cards=new ArrayList<Card>(); 
		usedCards=new ArrayList<Card>(); 
		openCards=new ArrayList<Card>(); 
		
		
		for(int i = 0; i < nDeck ;i++)  
		{ 
		
			for(Card.Suit s : Card.Suit.values()) 
			{ 
				for(int r = 1; r <= 13; r++) 
				{ 
					Card card = new Card(s,r); 
		
					cards.add(card); 
				} 
			} 
		}	 
		shuffle(); 
	} 
	public void shuffle() { 
		

		if(usedCards.size() != 0) 
			{ 

				for(Card c : usedCards) 
				{ 
					cards.add(c); 
				} 

				openCards.clear(); 
				usedCards.clear(); 
				nUsed = 0; 
			} 
		 
		Random rnd = new Random(); 
		for(int i = 0; i < cards.size(); i++) 
			{ 
				int j = rnd.nextInt(cards.size()); 
				Card tempCard = cards.get(j); 
				cards.set(j, cards.get(i)); 
				cards.set(i, tempCard); 
			} 
		} 
	public Card getOneCard(boolean isOpened){ 

		Card c = cards.get(0); 
		
		
		if(isOpened) 
			{ 
				openCards.add(c); 
			} 		
		usedCards.add(c); 
		nUsed++; 		
		cards.remove(c); 
		if (cards.size() == 0) 
			{ 
				shuffle(); 
			} 
		return c; 
	} 
	public ArrayList<Card> getAllCards(){ 
		return cards; 
	}	 
	public ArrayList<Card> getOpenedCard(){ 
		return openCards; 
	} 

}
