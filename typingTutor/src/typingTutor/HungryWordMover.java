package typingTutor;

public class HungryWordMover {
    private String hungryW; // the word
	private int posx; //position - width
	private int posy; // postion - height
	private int maxX; //maximum height
	private boolean across; //flag for if user does not manage to catch word in time
	
	private int fallingSpeed; //how fast this word is
	private static int maxWait=1000;
	private static int minWait=100;

	public static WordDictionary dict;
	
	HungryWordMover() { //constructor with defaults
		hungryW="computer"; // a default - not used
		posx=0;
		posy=0;	
		maxX=300;
		across=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}

    HungryWordMover(String text) { 
		this();
		this.text=text;
	}
	
	HungryWordMover(String text,int posx, int maxX) { //most commonly used constructor - sets it all.
		this(text);
		this.posx=posx; //only need to set x, word is at top of screen at start
		this.maxX=maxX;
	}


    
}
