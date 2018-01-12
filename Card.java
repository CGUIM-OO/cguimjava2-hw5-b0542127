
public class Card {
	enum Suit {Club, Diamond, Heart, Spade } 

	private Suit suit; //Clubs, Diamonds, Hearts, Spades 
	private int rank; //1~13 

	public Card(Suit s,int r){ 
		suit=s; 
		rank=r; 
	}	 

	public void printCard(){ 

		String s = ""; 

		String r = ""; 

		switch(suit) 
		{ 
			case Club: 
				s ="Club"; 
				break; 
			case Diamond: 
				s = "Diamond"; 
				break; 
			case Heart: 
				s = "Heart"; 
				break; 
			case Spade: 
				s = "Spade"; 
				break; 
		} 

		switch(rank) 
		{ 
			case 1: 
				r ="A"; 
				break; 
			case 11: 
				r = "J"; 
				break; 
			case 12: 
				r = "Q"; 
				break; 
			case 13: 
				r = "K"; 
				break; 

			default: 
				r = Integer.toString(rank); 
				break; 
		} 

 
 
		System.out.println(s+" "+r); 
	} 
	public Suit getSuit(){ 
		return suit; 
	} 
	public int getRank(){ 
		return rank; 
	} 


}
