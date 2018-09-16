import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String>categories = new ArrayList<>();
		ArrayList<String>words = new ArrayList<>();
		String category;
		Scanner userInput = new Scanner(System.in);
		Random random = new Random();
		boolean gameIsRunning = true;
		boolean wordIsGuessed = false;
		boolean categoryDoesntExist = false;
		int attempts = 10;
		int score = 0;
		
//		try{												   //Testing to see what is in the arraylists
//			MyFileReader2 mfr = new MyFileReader2(categories);
//			mfr.fillWords(category, words);
//			mfr.printArrayLists(categories, words);            
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
//		System.out.println("\nPrinting from test class\n");   // Testing the arraylists in this class
//		for(int i = 0; i < categories.size(); i++){
//			System.out.println(categories.get(i));
//		}
//		for(int i = 0; i < words.size(); i++){
//			System.out.println(words.get(i));
//		}
		
		
		
		while(gameIsRunning){
			System.out.println("Please choose a category: ");
			MyFileReader mfr = new MyFileReader(categories);
			category = userInput.nextLine(); 
			
			while(!categoryDoesntExist){
				if(checkIfCategoryExists(categories,category)){
					categoryDoesntExist = checkIfCategoryExists(categories,category);
				}else{
					category = userInput.nextLine();
				}
//				categoryDoesntExist = checkIfCategoryExists(categories,category);
//				category = userInput.nextLine();
			}
			
			mfr.fillWords(category, words);
			
			char[] randomWordToGuess = words.get(random.nextInt(words.size())).toLowerCase().toCharArray();
            char[] playerGuess = new char[randomWordToGuess.length];
            
            for (int i = 0; i < playerGuess.length; i++){
                if (randomWordToGuess[i] == ' '){
                    playerGuess[i] = ' ';
                }else{
                    playerGuess[i] = '_';
                }
            }
            
//            for(int i = 0; i < randomWordToGuess.length; i++){     // Testing the random word
//            	System.out.print(randomWordToGuess[i]);
//            }
            
            System.out.println();
           
            while(!wordIsGuessed && attempts > 0){
                System.out.println("Attempts left: " + attempts);
            	System.out.print("Current word/phrase: ");
                printUserCharArray(playerGuess);
                System.out.println("\nPlease enter a letter: ");
                char input = userInput.nextLine().charAt(0);
                boolean guessed1 = false;
                for (int i = 0; i < randomWordToGuess.length; i++){
                    if (randomWordToGuess[i] == input){
                        playerGuess[i] = input;
                        guessed1 = true;
                    }
                }
                if(!guessed1){
                	attempts--;
                }
                if(isTheWordGuessed(playerGuess)){
                    wordIsGuessed = true;
                    score++;
                    System.out.println("Congratulations you have revealed the word/phrase: ");
                    for (int i = 0; i<randomWordToGuess.length; i++){
                        System.out.print(randomWordToGuess[i]);
                        
                    }
                    System.out.println("\n\n\nCurrent score: " + score);
                }
                
            }
            if (!wordIsGuessed){
            	System.out.println("You ran out of guesses.");
            	System.out.println("Game Over");
    			gameIsRunning = false;
            }
            
			categories.clear();
			words.clear();
			wordIsGuessed = false;
			attempts = 10;
		}
	}
	
	public static boolean checkIfCategoryExists(ArrayList<String> categories, String category){ // changed -, boolean categoryDoesntExist
		for(int i = 0; i < categories.size(); i++){
			if(category.toLowerCase().equals(categories.get(i).toLowerCase())){
				return true; // changed from  return categoryDoesntExist = true;
			}
		}
			System.out.println("\nThe category you have entered does NOT exist.\nPlease choose a valid category:");
			for(int i = 0; i < categories.size(); i++){
				System.out.println(categories.get(i));
			}
			return false; // changed from  return categoryDoesntExist = false;
		
	}
	
	public static boolean isTheWordGuessed(char[] array){
		for (int i = 0; i < array.length; i++){
            if (array[i] == '_')return false;
        }
        return true;
	}
	public static void printUserCharArray(char[] playerGuess){
		for(int i = 0; i < playerGuess.length; i++){
        	System.out.print(playerGuess[i] + " ");
        }
	}

}
