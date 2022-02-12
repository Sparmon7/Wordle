import java.io.File;
import java.util.*;

public class Modified{
	ArrayList<String> possib = new ArrayList<String>();
	ArrayList<String> init = new ArrayList<String>();
	
	
	public Modified() throws Exception{
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
	}
	
	private void narrow(String guess, color[] res) {
		int j = possib.size() - 1;
		for(; j >=0; j--){

			String s = possib.get(j);
			boolean removed = false;
			int i = 0;
			while(!removed && i < 5) {
				char c = guess.charAt(i);
				if(numChars(c,s)< numRelChars(c,guess,res)) {
					possib.remove(s);
					removed = true;
				}else if(res[i] == color.green) {
					if(s.charAt(i) != c){
						possib.remove(s);
						removed = true;
					}
				}else if(res[i] == color.none){
					if(realN(c, guess, res) && numChars(c, s) > 0 ) {
						possib.remove(s);
						removed = true;
					}else if(s.charAt(i) == c){
						possib.remove(s);
						removed = true;
					}

				}else {
					if(s.indexOf(c) == -1|| s.charAt(i)==c){
						possib.remove(s);
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
	
}
