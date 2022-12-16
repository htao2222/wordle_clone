import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {

	static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static void main(String[] args) throws IOException {

		boolean found = false;
		BufferedReader bf = new BufferedReader(new FileReader("./src/5words.txt"));
		List<String> wordList = new ArrayList<String>();
		String line;
		while((line = bf.readLine()) != null) {
			wordList.add(line);
		}
		Random rand = new Random();
		int x = rand.nextInt(wordList.size());
		String word = wordList.get(x);
		System.out.println(word);

		System.out.println(ANSI_GREEN + "Welcome to Wordle Unofficial." + ANSI_RESET);
		char wChar[] = word.toCharArray();
		for(int a = 6; a > 0; a--) {
			System.out.println(ANSI_PURPLE + a + " attempts left." + ANSI_RESET);
			System.out.println("Please enter a 5 letter word.");
			String s = stdin.readLine();

			while(!wordList.contains(s)) {
				System.out.println("Invalid word. Please re-enter a valid word.");
				s = stdin.readLine();
			}
			System.out.print("\n");
//			while(s.length() != 5)
//			{
//				System.out.println("Invalid length. Please re-enter a 5 letter word.");
//				s = stdin.readLine();
//			}
			s = s.toLowerCase();

			if(s.equals(word)) {
				System.out.println(ANSI_GREEN + word + ANSI_RESET);
				found = true;
				break;
			}


			char sChar[] = s.toCharArray();

			for(int i = 0; i < 5; i++) {
				boolean c = false;
				for(int j = 0; j < 5; j++) {
					if(sChar[i] == wChar[j] && i==j) {
						//System.out.println(sChar[i] + " at character spot " + (i+1) + " matches exactly.");
						System.out.print(ANSI_GREEN + sChar[i] + ANSI_RESET);
						c = true;
						break;
					}
					if(sChar[i] == wChar[j]) {
						//System.out.println("The word contains the letter " + sChar[i] + " in a different spot.");
						System.out.print(ANSI_YELLOW + sChar[i] + ANSI_RESET);
						c= true;
						break;
					}

				}
				if(!c) {
					System.out.print(ANSI_RED + sChar[i] + ANSI_RESET);
				}
			}
			System.out.print("\n");
		}
		if(!found) {
			System.out.println("Failure! The word was " + ANSI_GREEN + word + ANSI_RESET);
		} else {
			System.out.println("Congrats you won, play again winner.");
		}
	}

}
