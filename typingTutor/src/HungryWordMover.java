package typingTutor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class HungryWordMover extends Thread {
    //private HungryWordMover hungryW; // the word

	private int fallingSpeed; //how fast this word is
	private static int maxWait=1000;
	private static int minWait=100;

	public  WordDictionary dict;

	private AtomicBoolean done;
	private AtomicBoolean pause;
	
	private String word; // the word
	private int x; //position - width
	private int y; // postion - height
	//private int maxY; //maximum height
	private int maxX; //maximum width
	private boolean crossed; //flag for if user does not manage to catch word in time
	//private boolean dropedx;

	private CountDownLatch startLatch;
	
	// HungryWordMover( String word,WordDictionary dict, Score score,
	// 		CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p) {
	// 	this(word);
	// 	this.startLatch = startLatch;
	// 	//this.score=score;
	// 	this.done=d;
	// 	this.pause=p;
	// }
	
	HungryWordMover() { //constructor with defaults
		word="computer"; // a default - not used
		x=0;
		y=0;	
		//maxY=300;
		maxX = 300;
		crossed=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}
	
	HungryWordMover(String text) { 
		this();
		this.word=text;
	}
	
	// only need to manituplae x 
	HungryWordMover(WordDictionary dict, String text,int y, int maxX, CountDownLatch latch, AtomicBoolean d, AtomicBoolean p) { //most commonly used constructor - sets it all.
		this(text);
		this.dict = dict;
		this.y=y; //only need to set x, word is at top of screen at start
		this.maxX=maxX;
		startLatch = latch;
		done = d;
		pause = p;
	}

	//constructor for hungryWord instance
	
	
	public static void increaseSpeed( ) {
		minWait+=50;
		maxWait+=50;
	}
	
	public static void resetSpeed( ) {
		maxWait=1000;
		minWait=100;
	}
	

// all getters and setters must be synchronized
	public synchronized  void setY(int y) {
		/*if (y>maxY) {
			y=maxY;
			dropped=true; //user did not manage to catch this word
		}*/
		this.y=y;
	}
	
	public synchronized  void setX(int x) {
		if( x > maxX){
			x = maxX;
			crossed = true;
		}
		this.x=x;
	}

	//method for setting new x value for hungry word
	public synchronized void setH(int x){
		if ( x> maxX){
			x=maxX;
			crossed=true;
		}
	}
	
	public synchronized  void setWord(String text) {
		this.word=text;
	}

	public synchronized  String getWord() {
		return word;
	}
	
	public synchronized  int getX() {
		return x;
	}	
	
	public synchronized  int getY() {
		return y;
	}
	
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}

	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
	public synchronized void resetPos() {
		//setY(0);
		setX(0);
	}

	//resetting x word position
	// public synchronized void resetX(){
	// 	setH(0);
	// }

	//method to reset hungry word after completion
	// public synchronized void resetWord() {
	// 	resetPos(); //needs to reset position on x axis
	// 	word=dict.getNewWord();
	// 	crossed=false;
	// 	fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	// 	//System.out.println(getWord() + " falling speed = " + getSpeed());
	// }

	
	public synchronized void resetHword(){
		resetPos(); ///resets position of word on x axis
		word = dict.getNewWord();
		crossed = false;
		fallingSpeed = (int)(Math.random() * (maxWait - minWait)+minWait);
	}
	
	/*public synchronized boolean matchWord(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.word)) {
			resetWord();
			return true;
		}
		else
			return false;
	}*/
	//drop x function
	
	//incrementing x instaed of y
	public synchronized  void drop(int inc) {
		setX(x+inc);
	}
	
	public synchronized  boolean crossed() {
		return crossed;
	}


		/// run method to move word across screen adapted from WordMover class
		public void run() {

			System.out.println(getWord() + " falling speed = " + getSpeed());
			try {
				System.out.println(getWord() + " waiting to start " );
				startLatch.await(); //mightnot need
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //wait for other threads to start
			System.out.println(getWord() + " started" );
			while (!done.get()) {				
				//animate the word
				while (!crossed() && !done.get()) {
					drop(10);
						try {
							sleep(getSpeed());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};		
						while(pause.get()&&!done.get()) {};
				}
				if (!done.get() && crossed()) {
					//score.missedWord();
					resetHword();
				}
				resetHword();
			}
		}
    
}
