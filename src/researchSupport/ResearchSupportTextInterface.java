package researchSupport;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Displays the text based interface to the ResearchSupportApplication. Gets the
 * details of the paper to be added/queried. Gets any additional necessary input
 * e.g. paper to be added as a reference or details of levels of display
 * required.
 */
public class ResearchSupportTextInterface {

	int action = 0;
	Paper paper = null;
	String refTitle = null;
	String paperTitle = null;
	int rating = 0;
	int levels = 0;

	public ResearchSupportTextInterface() {
		this.action = 0;
		this.rating = 0;
		this.refTitle = "";
		this.levels = 0;
	}

	/**
	 * Manages the display of the text-based display. Gets any necessary input.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {

		action = menu(); // Display the menu

		if ((action < 0) || (action > 10)) {
			System.out.println("\nInvalid choice. Try again\n");
		} else {

			// For valid choices other than "QUIT" or "LOAD", get title
			if ((action != 0) && (action != 10)) {

				paperTitle = inputPaperTitle();

				if (action == 1) {
					rating = inputLevelCountOrRating("rating");
					this.paper = new Paper(paperTitle, rating);

				} else if (action == 2) {
					refTitle = inputPaperTitle();

				} else if ((action == 8) || (action == 9)) {
					levels = inputLevelCountOrRating("level");
				}
			}
		}
	}

	/**
	 * Displays the menu and returns an integer representing the choice.
	 * 
	 * @return an integer in the range 0 - 10 representing the menu choice
	 */
	private int menu() {

		Scanner scan = new Scanner(System.in);

		System.out.println("\nPaper Repository Menu\n");
		System.out.println("1\tAdd a paper to the repository");
		System.out.println("2\tMake a paper a reference");
		System.out.println("3\tList paper details");
		System.out.println("4\tList direct citations");
		System.out.println("5\tList direct references");
		System.out.println("6\tList all citation chains up to the root");
		System.out.println("7\tList all reference chains to the lowest level");
		System.out.println("8\tList n-levels of citation");
		System.out.println("9\tList n-levels of references\n");
		System.out.println("10\tLoad Data");

		System.out.println("0\tEXIT\n");

		System.out.print("Enter menu choice, 0 to exit: ");

		String selection = scan.nextLine();
		
		while (!selection.matches("([0-9]|10)")) {
			System.out.print("Invalid choice. Try again: ");
			selection = scan.nextLine();
		}
		return Integer.parseInt(selection);
	}

	/**
	 * Gets the paper title from console input
	 * 
	 * @return title
	 * @throws IOException
	 */
	private String inputPaperTitle() throws IOException {

		Scanner scan = new Scanner(System.in);
		String title;
		System.out.print("Enter title : ");
		title = scan.nextLine();
		return title;
	}

	/**
	 * Accepts console input of an integer in the range 1 - 5.
	 * 
	 * @return a integer in the range 1 - 5
	 */
	private int inputLevelCountOrRating(String prompt) {
		Scanner scan = new Scanner(System.in);
		String sNumber = "";
		int number = 0;

		while ((number < 1) || (number > 5)) {
			System.out.print("Input " + prompt
					+ " as an integer in the range 1 - 5: ");
			try {
				sNumber = scan.nextLine();
				number = Integer.parseInt(sNumber);
			} catch (NumberFormatException e) {
				System.out.print("Please enter digits only: ");
				number = 0;
			}
		}
		return number;
	}

	/**
	 * 
	 * @return
	 */
	public int getAction() {
		return action;
	}

	/**
	 * 
	 * @return
	 */
	public Paper getPaper() {
		return paper;
	}

	/**
	 * 
	 * @return
	 */
	public int getLevels() {
		return levels;
	}

	/**
	 * 
	 * @return
	 */
	public String getRefTitle() {
		return refTitle;
	}

	/**
	 * 
	 * @return
	 */
	public String getPaperTitle() {
		return paperTitle;
	}

	/**
	 * 
	 * @param s
	 */
	public void print(String s) {
		System.out.println(s);
	}

	/**
	 * 
	 * @param papers
	 */
	public void print(Set<Paper> papers) {
		for (Paper p : papers) {
			System.out.println(p.toString());
		}
	}

	/**
	 * 
	 * @param chains
	 */
	public void print(HashSet<Stack<Paper>> chains) {
		for (Stack<Paper> s : chains) {
			for (Paper p : s) {
				/** Don't print an arrow on the last paper */
				System.out.print((s.indexOf(p) < s.size() - 1) ? p.getTitle()
						+ " -> " : p.getTitle());
			}
			System.out.println();
		}
	}
}
