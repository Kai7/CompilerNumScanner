import java.util.Scanner;
//import java.util.Map;    
import java.util.HashMap;

//import java.util.StringTokenizer;

public class Main {
	private static int[][] NumScannerDFATable;
	private static HashMap<Character, Integer> MapTable;

	public static void main(String[] args) {
		MapTable = new HashMap<Character, Integer>();
		buildMapTable();
		buildDFATable();

		Scanner Input = new Scanner(System.in);

		System.out.println("Enter q to quit");
		System.out.print("Input data: ");
		String HandleLine = Input.nextLine();

		while (!HandleLine.equals("q")) {
			if (isRegularNumber(HandleLine))
				System.out.println("It is a Number");
			else
				System.out.println("It is not a Number");

			System.out.print("Input data: ");
			HandleLine = Input.nextLine();
		}

		System.out.print("Quit");
		Input.close();
	}

	public static void buildMapTable() {
		char[] Digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8',	'9'};
		Character theChar = null;
		Integer theInt = new Integer(0);
		for (char Ch : Digits) {
			theChar = new Character(Ch);
			MapTable.put(theChar, theInt);
		}

		theChar = new Character('.');
		theInt = new Integer(1);
		MapTable.put(theChar, theInt);

		theChar = new Character('E');
		theInt = new Integer(2);
		MapTable.put(theChar, theInt);

		theChar = new Character('+');
		theInt = new Integer(3);
		MapTable.put(theChar, theInt);

		theChar = new Character('-');
		theInt = new Integer(3);
		MapTable.put(theChar, theInt);
	}

	public static void buildDFATable() {
		NumScannerDFATable = new int[9][];
		NumScannerDFATable[0] = new int[]{0, 0, 0, 0};
		NumScannerDFATable[1] = new int[]{2, 0, 0, 0};
		NumScannerDFATable[2] = new int[]{2, 3, 4, 0};
		NumScannerDFATable[3] = new int[]{5, 0, 0, 0};
		NumScannerDFATable[4] = new int[]{6, 0, 0, 7};
		NumScannerDFATable[5] = new int[]{5, 0, 8, 0};
		NumScannerDFATable[6] = new int[]{6, 0, 0, 0};
		NumScannerDFATable[7] = new int[]{6, 0, 0, 0};
		NumScannerDFATable[8] = new int[]{0, 0, 0, 7};

	}

	public static boolean isRegularNumber(String theInputToken) {
		int currentState = 1;
		int Index;
		for (int i = 0; i < theInputToken.length(); i++) {
			try {
				Index = MapTable.get(theInputToken.charAt(i));
				currentState = NumScannerDFATable[currentState][Index];
			} catch (NullPointerException e) {
				return false;
			}
		}

		if (currentState == 0)
			return false;
		return true;
	}
}
