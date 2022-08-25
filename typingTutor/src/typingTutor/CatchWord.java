package typingTutor;



import java.util.concurrent.atomic.AtomicBoolean;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	//FallingWord [] duplicate; //array to hold duplicate words
	FallingWord g;
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

			if(words[i].getWord().equals(target)){
				tempWord = words[i];

				if(tempWord != null && tempWord.getY() < words[i].getY()){
					tempWord = words[i];

				}
			}else{
				tempWord = words[i];
			}
			 g = words[i];
			
			
			i++;
		}
		 if(tempWord != null){ g = tempWord;}
		if(g.matchWord(target)){
			//tempWord.resetWord();
		System.out.println( " score! '" + target); //for checking
		score.caughtWord(target.length());	
		//FallingWord.increaseSpeed();
		//break;	
		
		}
	}	
		
		
}
