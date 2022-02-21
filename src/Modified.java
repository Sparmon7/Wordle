import java.io.File;
import java.util.*;

public class Modified{
	ArrayList<String> possib = new ArrayList<String>();
	ArrayList<String> init = new ArrayList<String>();
	ArrayList<String> copy = new ArrayList<String>();
	
	
	public Modified() throws Exception{
		//reads in possible guesses
		File file1 = new File("C:\\Users\\sparm\\OneDrive\\Desktop\\WORDLEGUESSES.txt");
	    Scanner sc = new Scanner(file1);
	    while(sc.hasNext()) {
		    init.add(sc.next().toUpperCase());
	    }
	   
	    //reads in possible answers
	    File file2 = new File("C:\\Users\\sparm\\OneDrive\\Desktop\\WORDLESOLUTIONS.txt");
	    Scanner sc1 = new Scanner(file2);
	    while(sc1.hasNext()) {
		    possib.add(sc1.next().toUpperCase());
	    }
	}
	
	enum color{
		green,
		yellow,
		none
	}
	
	//this one determines best guess
	public String best() {
		if(possib.size() == 1 || possib.size() == 2) {
			return possib.get(0);
		}else if(possib.size() == 0) {
			System.out.println("FAILURE");
			return "FAILURE!";
		}else{
			String best = "ERROR!";
			int lowestWorst = Integer.MAX_VALUE;
			for(int i = 0; i < init.size(); i++) {
				String s = init.get(i);
				int elim = worstCase(s, lowestWorst);
				if(elim < lowestWorst) {
					lowestWorst = elim;
					best = s;
				}
				//System.out.println(s + elim);
			}
			return best;
		}
				
	}
	//
	private int worstCase(String s, int lowestWorst) {
		int worst = 0;
		for(int i= 0; i < 243; i++) {
			String res = "";
			int cur = i;
			for(int dig = 0; dig < 5; dig++) {
				int rem = cur%3;
				if(rem == 0){
					res+= "N";
				}else if(rem == 1) {
					res+= "Y";
				}else {
					res+= "G";
				}
				cur-= rem;
				cur /=3;
			}
			int size = altGuess(s, res);
			if(size> worst) {
				worst = size;
			}
			if(worst > lowestWorst) {
				return worst;
			}
		}
		return worst;
	}
	
	
	public int altGuess(String guess, String res) {
		guess = guess.toUpperCase();
		res = res.toUpperCase();
		color[] result = new color[5];
		for(int i = 0; i < 5; i++) {
			if(res.charAt(i) == 'G') {
				result[i] = color.green;
			}else if(res.charAt(i) == 'N') {
				result[i] = color.none;
			}else {
				result[i] = color.yellow;
			}
		}
		narrow(guess, result);
		return copy.size();
	}
	
	private boolean realN(char c, String guess, color[] result) {
		for(int i = 0; i < guess.length(); i++) {
			if(guess.charAt(i) == c && result[i] != color.none) {
				return false;
			}
		}
		return true;
	}
	
	public void guess(String guess, String res) {
		guess = guess.toUpperCase();
		res = res.toUpperCase();
		color[] result = new color[5];
		for(int i = 0; i < 5; i++) {
			if(res.charAt(i) == 'G') {
				result[i] = color.green;
			}else if(res.charAt(i) == 'N') {
				result[i] = color.none;
			}else {
				result[i] = color.yellow;
			}
		}
		narrow(guess, result);
		possib.clear();
		for(int k = 0; k < copy.size(); k++) {
			possib.add(copy.get(k));
		}
	}
	
	private void narrow(String guess, color[] res) {
		//THIS IS WHAT NEEDS TO BE FIXED!!!
		copy.clear();
		for(int k = 0; k < possib.size(); k++) {
			copy.add(possib.get(k));
		}
		int j = copy.size() - 1;
		for(; j >=0; j--){
			
			String s = copy.get(j);
			boolean removed = false;
			int i = 0;
			while(!removed && i < 5) {
				char c = guess.charAt(i);
				if(numChars(c,s)< numRelChars(c,guess,res)) {
					copy.remove(s);
					removed = true;
				}else if(res[i] == color.green) {
					if(s.charAt(i) != c){
						copy.remove(s);
						removed = true;
					}
				}else if(res[i] == color.none){
					if(realN(c, guess, res) && numChars(c, s) > 0 ) {
						copy.remove(s);
						removed = true;
					}else if(s.charAt(i) == c){
						copy.remove(s);
						removed = true;
					}

				}else {
					if(s.indexOf(c) == -1|| s.charAt(i)==c){
						copy.remove(s);
						removed = true;
					}
				}
				i++;
			}
		}
	}
	
	private int numChars(char c, String s) {
		int ret = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == c) {
				ret++;
			}
		}
		return ret;
	}
	
	private int numRelChars(char c, String s, color[] res) {
		int ret = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == c && res[i] != color.none) {
				ret++;
			}
		}
		return ret;
	}
	
	
	public String oppAsc(int i) {
		if(i == 0) {
			return "A";
		}else if(i == 1) {
			return "B";
		}else if(i == 2) {
			return "C";
		}else if(i == 3) {
			return "D";
		}else if(i == 4) {
			return "E";
		}else if(i == 5) {
			return "F";
		}else if(i == 6) {
			return "G";
		}else if(i == 7) {
			return "H";
		}else if(i == 8) {
			return "I";
		}else if(i == 9) {
			return "J";
		}else if(i == 10) {
			return "K";
		}else if(i == 11) {
			return "L";
		}else if(i == 12) {
			return "M";
		}else if(i == 13) {
			return "N";
		}else if(i == 14) {
			return "O";
		}else if(i == 15) {
			return "P";
		}else if(i == 16) {
			return "Q";
		}else if(i == 17) {
			return "R";
		}else if(i == 18) {
			return "S";
		}else if(i == 19) {
			return "T";
		}else if(i == 20) {
			return "U";
		}else if(i == 21) {
			return "V";
		}else if(i == 22) {
			return "W";
		}else if(i == 23) {
			return "X";
		}else if(i == 24) {
			return "Y";
		}else{
			return "Z";
		}
	}
	
	public int asc(String n) {
		if(n.equals("A")) {
			return 0;
		}else if(n.equals("B")) {
			return 1;
		}else if(n.equals("C")) {
			return 2;
		}else if(n.equals("D")) {
			return 3;
		}else if(n.equals("E")) {
			return 4;
		}else if(n.equals("F")) {
			return 5;
		}else if(n.equals("G")) {
			return 6;
		}else if(n.equals("H")) {
			return 7;
		}else if(n.equals("I")) {
			return 8;
		}else if(n.equals("J")) {
			return 9;
		}else if(n.equals("K")) {
			return 10;
		}else if(n.equals("L")) {
			return 11;
		}else if(n.equals("M")) {
			return 12;
		}else if(n.equals("N")) {
			return 13;
		}else if(n.equals("O")) {
			return 14;
		}else if(n.equals("P")) {
			return 15;
		}else if(n.equals("Q")) {
			return 16;
		}else if(n.equals("R")) {
			return 17;
		}else if(n.equals("S")) {
			return 18;
		}else if(n.equals("T")) {
			return 19;
		}else if(n.equals("U")) {
			return 20;
		}else if(n.equals("V")) {
			return 21;
		}else if(n.equals("W")) {
			return 22;
		}else if(n.equals("X")) {
			return 23;
		}else if(n.equals("Y")) {
			return 24;
		}else{
			return 25;
		}
	}
	
	public void print(){
		System.out.println();
		System.out.print("Possible solutions: ");
		for(int i = 0; i < possib.size(); i++) {
			System.out.print(possib.get(i) + " ");
		}
		System.out.println();
		System.out.println("Size: "+ possib.size());
	}
	
}
