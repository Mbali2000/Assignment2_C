package typingTutor;



import java.util.concurrent.atomic.AtomicBoolean;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	FallingWord [] duplicate = new FallingWord[words.length]; //array to hold duplicate words
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
		int j = 0;
		while (i<noWords) {		
			while(pause.get()) {};
			//check for duplicate before executing matchWord method

			if(words[i].getWord().equals(target)){
				duplicate[i] = words[i];

				for(FallingWord word : duplicate){
					if(word != null && word.getY() < words[i].getY()){
						tempWord = words[i];
	
					}
				}
				//j++;
			}else{
				tempWord = words[i];
			}
		System.out.println("hap");
		
		/*if(tempWord.matchWord(target)){
			System.out.println( " score! '" + target); //for checking
			score.caughtWord(target.length());	
			//FallingWord.increaseSpeed();
			break;	
			
		}*/
			
			i++;
		}
		System.out.println("penin");
		if(words[i].matchWord(tempWord.getWord())){
		System.out.println( " score! '" + target); //for checking
		score.caughtWord(target.length());	
		//FallingWord.increaseSpeed();
		//break;	
		
		}
		
	}	
		
		
}
