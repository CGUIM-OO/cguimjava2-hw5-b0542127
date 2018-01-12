import java.util.ArrayList;
import java.util.List;

public class Table {

	private final int MAXPLAYER = 4;
	private Deck allDeck;
	private Player[] allPlayers;
	private Dealer oneDealer;
	private int[] pos_betArray;
	private Card dealerFaceUp;
	private ArrayList<Card> deca = new  ArrayList<Card>();
	private List[] a = new List[MAXPLAYER];
	
	
	public Table(int nDeck) {
		// TODO Auto-generated constructor stub
		allDeck=new Deck(nDeck);
		allPlayers=new Player[MAXPLAYER];
		oneDealer=new Dealer();
		pos_betArray=new int[MAXPLAYER];
		for(int i=0;i<MAXPLAYER;i++) {
			a[i]= new ArrayList<Card>();
		}
	}
	
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	
	public void set_player(int pos, Player p) {
		allPlayers[pos]=p;
	}
	public Player[] get_player() {
		return allPlayers;
		
	}
	public void set_dealer(Dealer d) {
		oneDealer=d;
	}
	public Card get_face_up_card_of_dealer() {
		return dealerFaceUp;
		
	}
	private void ask_each_player_about_bets() {
		for(int i=0;i<MAXPLAYER;i++) {
			allPlayers[i].sayHello();
			pos_betArray[i]=allPlayers[i].make_Bet();
		}
	}
	private void distribute_cards_to_dealer_and_players() {
		
		deca.add(allDeck.getOneCard(false));
		dealerFaceUp=allDeck.getOneCard(true);
		deca.add(dealerFaceUp);
		oneDealer.setOneRoundCard(deca);
		
		
		System.out.print("Dealer's face up card is "); 
		dealerFaceUp.printCard();
		for(int i=0;i<MAXPLAYER;i++) {
			    //
			a[i].add(allDeck.getOneCard(true));
			a[i].add(allDeck.getOneCard(true));
			allPlayers[i].setOneRoundCard((ArrayList<Card>) a[i]);
			
			System.out.print(allPlayers[i].getName()); 
			System.out.print("'s Cards now:"); 
			allPlayers[i].printAllCard();
			
		}
		
	}
	private void ask_each_player_about_hits() {
		for(int i=0;i<MAXPLAYER;i++) {
			boolean bo;
			do {
				if(allPlayers[i].getTotalValue()>21) {
					bo=false;
				}else {
					bo=allPlayers[i].hit_me(this);
					
				}
				if(bo) {
					System.out.print("Hit! "+ allPlayers[i].getName()+ "¡¦s cards now: ");
					
					a[i].add(allDeck.getOneCard(true));
					allPlayers[i].setOneRoundCard((ArrayList<Card>) a[i]);
					allPlayers[i].printAllCard();
				}else {
					System.out.println(allPlayers[i].getName()+ "Pass hit!");
					
					System.out.println(allPlayers[i].getName()+"'s hit is over!");
				}
				
			}while(bo);
		}	
	}
	private void ask_dealer_about_hits() {
		boolean bo = true;
		while(bo) {
			if(oneDealer.getTotalValue()>21) {
				bo=false;	
			}else {
				bo=oneDealer.hit_me(this);
				deca.add(allDeck.getOneCard(true));
			}
		}
		System.out.print("Dealer's hit is over!");
		
	}
	
	 private void calculate_chips() {
		 System.out.print("Dealer's card value is "+oneDealer.getTotalValue()+" ,Cards:");
		 oneDealer.printAllCard();
		 for(int i=0;i<MAXPLAYER;i++) {
			 System.out.print(allPlayers[i].getName()+"'s Cards: ");
			 allPlayers[i].printAllCard();
			 System.out.print(allPlayers[i].getName()+" card value is "+allPlayers[i].getTotalValue());
			 if(oneDealer.getTotalValue()>21 && allPlayers[i].getTotalValue()>21 ) {
				 System.out.println(",chips have no change! The Chips now is: "+allPlayers[i].get_current_chips());
			 }else if(allPlayers[i].getTotalValue() > 21){
				 allPlayers[i].increaseChips(pos_betArray[i]*(-1));
				 System.out.println(", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+allPlayers[i].get_current_chips());
				 
			 }else if(oneDealer.getTotalValue() > 21){
				 allPlayers[i].increaseChips(pos_betArray[i]);
				 System.out.println(", Get "+pos_betArray[i]+" Chips, the Chips now is: "+allPlayers[i].get_current_chips());
				 			 
				 
			 }else if(oneDealer.getTotalValue() > allPlayers[i].getTotalValue()){
				 allPlayers[i].increaseChips(pos_betArray[i]*(-1));
				 System.out.println(", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+allPlayers[i].get_current_chips());
				 
			 }else if(oneDealer.getTotalValue() < allPlayers[i].getTotalValue()){
				 allPlayers[i].increaseChips(pos_betArray[i]);
				 System.out.println(", Get "+pos_betArray[i]+" Chips, the Chips now is: "+allPlayers[i].get_current_chips());
				 
			 }else if(oneDealer.getTotalValue() == allPlayers[i].getTotalValue()){
				 System.out.println(",chips have no change! The Chips now is: "+allPlayers[i].get_current_chips());
			 }
		 }
		 for(int i=0;i<MAXPLAYER;i++)
			 a[i].clear();
		 deca.clear();
	 }
	
	
	 public int[] get_players_bet() {
		int[] oo = new int[MAXPLAYER];
		for(int i=0;i<MAXPLAYER;i++) {
			oo[i]=allPlayers[i].get_current_chips();
		}
		 
		 
		 return oo;
		 
	 }
	

}
