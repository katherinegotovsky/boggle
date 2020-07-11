package katherine.gotovsky;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

	public class Boggle { 
		
		public static void main(String[] args) throws IOException {
			ArrayList<String> words = new ArrayList<>();
			
			Scanner in = new Scanner(System.in);
			int score = 0;

			try {
				String fileName = "Dictionary.txt";
				File inputFile = new File(fileName);
				Scanner inFile = new Scanner(inputFile);
				
				while (inFile.hasNextLine()) {
					String text = inFile.nextLine().toUpperCase();
					words.add(text);
				}
			} 
			
			catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			
			Board board = new Board();
			System.out.println("You have thirty seconds. Type as many words as you can!");
			long startIndexTime = System.currentTimeMillis();
			long end = startIndexTime + 30*1000;
			ArrayList <String> usedwords = new ArrayList<>();
			System.out.println(board);
			
			while (System.currentTimeMillis() < end) {
				String newword = in.nextLine().toUpperCase();
				
				while (!words.contains(newword) || newword.length() <= 2 || newword.length() > 16 || 
						usedwords.contains(newword) || !board.checkWord(newword)) {
					System.out.println("Your word is not valid.");
					newword = in.nextLine().toUpperCase();
				}

				board.resetBoard();				
				usedwords.add(newword);
				score += newword.length()*2; //add score
				System.out.println("Added! +" + newword.length()*2);
			}
			System.out.print("Time's up! Your score is " + score);

		}
	}
	