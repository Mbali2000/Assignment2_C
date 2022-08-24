package typingTutor;



import java.util.concurrent.atomic.AtomicBoolean;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	FallingWord duplicate; //variable to hold duplicate word
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
			if(target == words[i].getWord()){
				duplicate = words[i];
				//checks for lowest y coordinate value
				if(words[i].getY() > words[i+1].getY()){
					duplicate = words[i+1];
				}else{
					duplicate = words[i];
				}
				
			}
			

			if (duplicate.matchWord(target)) {
				System.out.println( " score! '" + target); //for checking
				score.caughtWord(target.length());	
				//FallingWord.increaseSpeed();
				break;
			}
			i++; //checks all possible duplicates
		   
		}
		
	}	
}
