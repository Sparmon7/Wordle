import java.io.File;
import java.util.Scanner;
import java.util.*;
public class Possible {
	
	ArrayList<String> possib = new ArrayList<String>();
	ArrayList<String> init = new ArrayList<String>();
	ArrayList<String> greens = new ArrayList<String>();
	ArrayList<String> yellows = new ArrayList<String>();
	ArrayList<String> yellowPlaces = new ArrayList<String>();
	ArrayList<String> greenPlaces = new ArrayList<String>();
	
	
	public Possible() throws Exception{
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
	
	//tallies number of letters in each location
	public String best(){
		int[][] tab = new int[6][26];
		for(String s: possib) {
			for(int i=0; i < 5; i++) {
				tab[i][asc(s.substring(i,i+1))]++;
			}
		}
		
		
		//totals number of each letter
		for(int a = 0; a < 26; a++) {
			int count = 0;
			for(String s: greenPlaces) {
				if(s.substring(0,1).equals(oppAsc(a))){
					count++;
				}
			}

			tab[5][a] = tab[0][a] + tab[1][a] + tab[2][a] + tab[3][a] + tab[4][a] - (count*possib.size());
		
		}
		
		//DOWN TO CHECKING HERE AND CHECK WHERE ADDING GREEN PLACES
		
		
		
		
		
		
		
		
		
		
		
		
		
		//scores each possible guess based on letters and placement (THE MAIN PART OF THE ALGORITHM)
		String ret2 = "";
		int maxScore = -1;
		for(String b: init) {
			int score = 0;
			int score2 = 0;
			for(int i = 0; i < b.length(); i++) {
				String now = b.substring(i,i+1);
				if(!b.substring(0,i).contains(now) && !yellowPlaces.contains(now + String.valueOf(i)) && !greenPlaces.contains(now + String.valueOf(i))) {
					Boolean okay = true;
					if(yellows.contains(now)||greens.contains(now)) {
						score+= tab[i][asc(now)];
					}
						
					if(okay) {
						score += tab[5][asc(now)];
					}
				}
			}
			if(score > maxScore){
				maxScore = score;
				ret2 = b;
				score2 = 0;
				for(int h=0; h < 5; h++) {
					Boolean fine = true;
					for(int g=0; g < greenPlaces.size();g++) {
						if(Integer.valueOf(greenPlaces.get(g).substring(1)) == h) {
							fine = false;
						}
					}
					if(fine) {
						score2 += tab[h][asc(b.substring(h,h+1))];
					}
				}
				//settles ties
			}else if(score == maxScore) {
				int secondary = 0;
				for(int h=0; h < 5; h++) {
					Boolean fine = true;
					for(int g=0; g < greenPlaces.size();g++) {
						if(Integer.valueOf(greenPlaces.get(g).substring(1)) == h) {
							fine = false;
						}
					}
					if(fine) {
						secondary += tab[h][asc(b.substring(h,h+1))];
					}
				}
				if(secondary >= score2) {
					ret2 = b + " " + ret2;
					score2 = secondary;
				}else {
					ret2 += " " + b;
				}
			}
		}
		if(possib.size() == 1) {
			return possib.get(0);
		}
		
		if(possib.size() == 2) {
			return possib.get(0) + " " + possib.get(1);
		}
		
		if(possib.size() == 0) {
			return "ERROR";
		}
		//return "Best Possible Solution: " + cur + "  
		return  ret2;
	}
	
	public void guess(String guess, String result){
		//USE NYG
		for(int i = 0; i < 5; i++) {
			if(result.substring(i,i+1).equals("N")) {
				//figures out if not actually in word or should be treated as a yellow
				boolean good = false;
				for(int mine = 0; mine < 5; mine++) {
					if(guess.substring(mine,mine+1).equals(guess.substring(i,i+1)) && !result.substring(mine,mine+1).equals("N") ) {
						good = true;
					}
					
				}
				if(good) {
					yellow(guess.substring(i,i+1), i);
					yellowPlaces.add(guess.substring(i,i+1) + String.valueOf(i));
					yellows.add(guess.substring(i,i+1));
				}else {
					none(guess.substring(i,i+1));
				}
			}else if(result.substring(i,i+1).equals("Y")) {
				yellow(guess.substring(i,i+1), i);
				yellowPlaces.add(guess.substring(i,i+1) + String.valueOf(i));
				yellows.add(guess.substring(i,i+1));
			}else{
				green(guess.substring(i,i+1),i);
			}
		}
		//checks to see if all remaining share a common letter and if so, treats as green
		for(int j = 0; j < 5; j++) {
			Boolean same = false;
			int k =0;
			if(possib.size() >= 1) {
				same = true;
				while(same && k < possib.size() - 1) {
					if(!possib.get(k).substring(j,j+1).equals(possib.get(k+1).substring(j,j+1))) {
						same = false;
					}
					k++;
				}
			}

			if(same) {
				String letter = possib.get(0).substring(j,j+1);
				if(!greens.contains(letter)) {
					greens.add(letter);
					if(!greenPlaces.contains(letter+String.valueOf(j))) {
						greenPlaces.add(letter + String.valueOf(j));
					}
				}
			}
		}
	}
	
	public void none(String n) {
		for(int i = possib.size() - 1; i >= 0; i--) {
			String cur = possib.get(i);
			if(cur.contains(n)) {
				possib.remove(i);
			}
		}
	}
	
	public void yellow(String n, int j) {
		for(int i = possib.size() - 1; i >= 0; i--) {
			String cur = possib.get(i);
			if(cur.substring(j,j+1).equals(n)) {
				possib.remove(i);
			}
			if(!cur.contains(n)) {
				possib.remove(i);
			}
		}
	}
	
	public void green(String n, int j) {
		for(int i = possib.size() - 1; i >= 0; i--) {
			String cur = possib.get(i);
			if(!cur.substring(j,j+1).equals(n)) {
				possib.remove(i);
			}
		}
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
	
	
	//prints possible solutions
	public Boolean print() {
		if(possib.size() > 1) {
			System.out.print("" + possib.size() + " possible solutions: ");
			for(String s: possib) {
				System.out.print(s + " ");
			}
			System.out.println();
			return false;
		}
		return true;
		
	}

}