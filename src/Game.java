
public class Game {
	private String solution;
	
	public Game(String s){
		solution = s.toUpperCase();
	}
	
	//actual gameplay
	public String play(String s) {
		s=s.toUpperCase();
		String ret = "";
		if(s.length() == 5 && s.matches("[a-zA-Z]+")){
			for(int i = 0; i< 5; i++) {
				String cur = s.substring(i,i+1);
				if(solution.substring(i,i+1).equals(cur)) {
					ret+= "G";
				}else if(solution.contains(cur)){
					if(container(solution, cur) >= container(s, cur) ) {
						ret+= "Y";
					}else{
						//checks for yellows or nones since real wordle has weird rules about doubles
						int greens = 0;
						for(int j = 0; j < 5; j++) {
							if(solution.substring(j,j+1).equals(s.substring(j,j+1))&&s.substring(j,j+1).equals(cur)) {
								greens++;
							}
						}
						int pre = container(s.substring(0,i), cur);
						if(container(solution,cur) == greens) {
							ret+= "N";
						}else {
							//account for when there are more in the solution than there are greens
							if(pre >= container(solution,cur) - greens) {
								ret+= "N";
							}else {
								ret+= "Y";
							}
						}
					}
				}else {
					ret+= "N";
				}
			}
		}
		return ret;
		
	}
	

	//counts how many of a single character is in a string
	private int container(String whole, String s) {
		int count = 0;
		for(int i = 0; i < whole.length(); i++) {
			if(whole.substring(i,i+1).equals(s)) {
				count++;
			}
		}
		return count;
	}
	

}

