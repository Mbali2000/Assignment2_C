package typingTutor;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	FallingWord [] duplicate = new FallingWord[words.length]; //array to hold duplicate words
	//FallingWord g;
	FallingWord tempWord = null;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static int noWords; //how many
	private static Score score; //user score
	
	CatchWord(String typedWord) {
		target=typedWord;
	}
	
	public static void setWords(FallingWord[] wordList) {
		words=wordList;	
		noWords = words.length;
	}
	
	public static void setScore(Score sharedScore) {
		score=sharedScore;
	}
	
	public static void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}
	
	public void run() {
		int i=0;
		
		while (i<noWords) {		
			while(pause.get()) {};
			//check for duplicate before executing matchWord method
		 	tempWord = words[i];

			if(tempWord.getWord().equals(target)){
				duplicate[i] = words[i];

			}

		 	if(tempWord.getWord().equals(target)){
		 		duplicate[i] = words[i];
		 		//System.out.println("hap");
				
		 	}
		
		
	
		 
		
			
			i++;
		}
		for(FallingWord word : duplicate){
			if(word != null && word.getY() > tempWord.getY()){
				System.out.println("win");
				tempWord = word;

			}
		}
		duplicate = new FallingWord[words.length];
	   // System.out.println(tempWord.getWord());
	   
	   if(tempWord.matchWord(target)){
	   System.out.println( " score! '" + target); //for checking
	   score.caughtWord(target.length());	
	   //FallingWord.increaseSpeed();
	   //break;	
	   
	   }
		
		
	}	
		
		
}
