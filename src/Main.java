import java.io.File;
import java.util.Scanner;
import java.util.*;
public class Main {
	public static void main(String[] args) throws Exception{
//		Modified test= new Modified();
//		String guess = "lsits";
//		String res = "nynny";
//		test.guess(guess, res);
//		
//		Possible tester = new Possible();
//		tester.guess(guess, res);
//		tester.print();
//		System.out.println("GUESS: " + tester.best());
		
		//whether I want to measure stats or I want to use helper
		Boolean statistics = false;
		
		if(statistics) {
			//THIS MEASURES STATISTICS OF THE ALGORITHM AS A WHOLE
			//CURRENT AVERAGE NUMBER OF GUESSES IS 3.7 WITH ALL BEING WITHIN 6
			int sum = 0;
			int max  =0;
			String bads = "";
			Possible orig = new Possible();
			for(String s: orig.possib) {
				int guesses = 0;
				System.out.println();
				System.out.println(s);
				Game tester = new Game(s);
				Possible test = new Possible();
				Boolean finished = false;
				String res;
				while(!finished) {
					res = tester.play(test.best().substring(0,5));
					System.out.println(test.best().substring(0,5) + " " + res);
					guesses++;
					if(res.equals("GGGGG")) {
						finished = true;
					}else {
						test.guess(test.best().substring(0,5), res);
					}
				}
				sum+= guesses;
				if(guesses == max) {
					bads+= s + " ";
				}else if(guesses> max) {
					bads = s + " ";
					max= guesses;
				}
				System.out.println(guesses);
			}
			System.out.println();
			System.out.println();
			System.out.println(((double)sum)/ orig.possib.size());
			System.out.println(max);
			System.out.println(bads);
			
		}else {
			//This is the Wordle helper, which runs through the console
			Modified tester = new Modified();
			System.out.println(tester.best());
//			Possible test = new Possible();		
//			Scanner scanner = new Scanner(System.in);
//			System.out.println("Welcome to the Wordle Helper, an app made to assist you in beating the Wordle! It provides helpful guesses to eliminate many words, while also listing every possible word :)");
//			System.out.println("Guess: " + test.best());
//			Boolean done = false;
//			while(!done) {
//				Boolean guessy = false;
//				String g = "";
//				while(!guessy) {
//					System.out.println("Please type in your guess and press ENTER");
//					g = scanner.nextLine().toUpperCase();
//					if(g.length() == 5 && g.matches("[a-zA-Z]+")) {
//						guessy = true;
//					}
//				}
//				
//				
//				Boolean resulty = false;
//				String result = "";
//				while(!resulty) {
//					resulty = true;
//					System.out.println("Please type in the result of that guess where G is Green, Y is Yellow, and N is gray, and press ENTER. (for instance, GGNNY or NNNYG)");
//					result = scanner.nextLine().toUpperCase();
//					int i = 0;
//					for(i = 0; i < result.length(); i++) {
//						String s = result.substring(i,i+1).toUpperCase();
//						if(!(s.equals("Y") || s.equals("G")|| s.equals("N"))) {
//							resulty = false;
//						}
//					}
//					if(i !=5) {
//						resulty = false;
//					}
//				}
//
//				
//				test.guess(g, result);
//				done = test.print();
//
//				System.out.println("GUESS: " + test.best());
//			}
//			System.exit(0);
		}
	
		

		
		//SHARABLE WITH STATS REDDIT/TWITTER/LINKEDIN/WEB WITH JAVA WEB FRAMEWORK???
		//CHARACTERS INSTEAD OF SUBSTRING
		//COMMENTS AND VARIABLE NAMES
		//3 blue 1 brown
		//read me
		//MAKE IT SO NOT PRINTING "GUESS: ERROR"
		//REGEX
		//ENUMS and chars
		//comments andclear variables
		//UPLOAD TXT FILES AND RELATIVE PATHS; GOOGLE JAVA RELATIVE PATH 
		//two yellows or yellow and green of same letter make so word must have two in it or 3, etc.
		//PERFECT ALGORITHM WITH MINIMIZE SPREAD OF POSSIBLES
		//Algortihm must optimize based on already greens/yellows so that only in most common places if poss has them there!
		
		
		//MAKE SURE GUESSES ARE ALWAYS MINIMIZED AND SMALLER THAN POSSIBLE, THNE WORK ON GUESSING
		//DEBUG the 0'S!!!

	}

}
