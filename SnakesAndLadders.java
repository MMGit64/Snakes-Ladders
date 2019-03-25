import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;



public class SnakesAndLadders {									//Main method to start the game using the 'conditions' and 'start game' methods

	public static void main(String[] args) {
		SnakeLadderConditions snakeLadders = new SnakeLadderConditions();
		snakeLadders.startGame();

	}

}



class SnakeLadderConditions {								//Conditions to be set
	
	final static int WINPOINT = 100;						//100 is the winning point
	
	
	static Map<Integer,Integer> snake = new HashMap<Integer,Integer>();
	static Map<Integer,Integer> ladder = new HashMap<Integer,Integer>(); 
	
	{
		snake.put(99,54);
		snake.put(70,55);
		snake.put(52,42);
		snake.put(25,2);
		snake.put(95,72);
		
		ladder.put(6,25);
		ladder.put(11,40);
		ladder.put(60,85);
		ladder.put(46,90);
		ladder.put(17,69);
	}
	
	
	
	public int rollDice() {							//Setting the dice to be randomised when rolled
		int dice = 0;
		
		Random random = new Random();
		dice = random.nextInt(7);
		
		return (dice == 0?1:dice);
	}
	
	
	
	public int calculatePlayerValue(int player, int diceNum) {		// Defines what happens when a player lands a snake or ladder position.
		player = player + diceNum;
		
		if(player > WINPOINT)
		{
			player = player - diceNum;
			return player;
		}
		
		if(null!=snake.get(player))
		{
			System.out.println("Snake ate you! YUM YUM!\n");
			player= snake.get(player);
		}
		
		if(null!=ladder.get(player))
		{
			System.out.println("Up the ladder! The User Rises!\n");
			player= ladder.get(player);
		}
		return player;
	}
	
	
	public boolean isWin(int player) {					//Sets up the winning position referring back to 'WINPOINT' in 'snakes&laddersCondition' method
		return WINPOINT == player;
	}
	

	
	public void startGame() {							// 1) Starts the game							
											// 2) Ensure that as long as currentPlayer = 1, the first player will start
		int FirstPlayer = 0, SecondPlayer = 0;					// 3) By pressing 'r' for roll, 'diceValue' will refer to the conditions of 'rollDice' method 																		
		int currentPlayer = 1;																						
		Scanner scanner = new Scanner(System.in);
		String roll;
		int diceValue = 0;
		
		do {																								
			System.out.println(currentPlayer==1?"\n\nFIRST PLAYER TURN":"\n\nSECOND PLAYER TURN");
			System.out.println("Press r to roll Dice");
			roll = scanner.next();
			diceValue = rollDice();
			
			
			if(currentPlayer == 1) {
				FirstPlayer = calculatePlayerValue(FirstPlayer,diceValue);		// 4) Refers to 'calculatePlayerValue' method to determine players current positions
				System.out.println("First Player Score	: " + FirstPlayer);
				System.out.println("Second Player Score	: " + SecondPlayer);
				System.out.println("------------------");
				
				if(isWin(FirstPlayer)) {						// 5) Refers to 'isWin' method that determines when a player reaches 100.
					System.out.println("FIRST PLAYER WINS!!!");
					return;
				}
			}
			else {
				SecondPlayer = calculatePlayerValue(SecondPlayer,diceValue);
				System.out.println("First Player Score	: " + FirstPlayer);
				System.out.println("Second Player Score	: " + SecondPlayer);
				System.out.println("------------------");
				
				if(isWin(SecondPlayer)) {
					System.out.println("SECOND PLAYER WINS!!!");
					return;
				}
			} currentPlayer = -currentPlayer;					// 6) Allows switching of players
			
		}while("r".equals(roll));
	
	}


}










