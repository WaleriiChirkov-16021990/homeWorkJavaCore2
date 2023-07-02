import java.util.Random;
import java.util.Scanner;


/*
Написать метод, возвращающий количество чётных элементов массива.
countEvens([2, 1, 2, 3, 4]) → 3 countEvens([2, 2, 0]) → 3 countEvens([1, 3, 5]) → 0
Написать функцию, возвращающую разницу между самым большим и самым маленьким элементами переданного не пустого массива.
Написать функцию, возвращающую истину, если в переданном массиве есть два соседних элемента, с нулевым значением.
 */

public class Main {
	public static void main(String[] args) {
		System.out.println("Метод посчитал количество четных элементов");
		System.out.println(getCountEvenNumbered(new int[]{1, 2, 3, 4, 6, 7}));
		System.out.println("Метод посчитал разницу максимального и минимального элементов массива");
		System.out.println(getMaxMinDifference(new int[]{1, 2, 3, 4, 6, 7}));
		System.out.println("Метод проверил встречаются ли 2 соседних элемента массива со значением = 0");
		System.out.println(isDoubleZero(new int[]{1, 0, 3, 0, 6, 0}));
		System.out.println("Метод проверил встречаются ли 2 соседних элемента массива со значением = 0");
		System.out.println(isDoubleZero(new int[]{1, 0, 3, 4, 0, 0}));
	}
	
	/**
	 * Метод считает количество четных целочисленных элементов переданного массива
	 * @param array - не пустой массив целочисленных элементов
	 * @return int count = countEvens
	 */
	private static int getCountEvenNumbered(int[] array) {
		int counter = 0;
		if (array.length == 0) {
			return counter;
		}
		for (int number :
				array) {
			if (number % 2 == 0) {
				counter += 1;
			}
		}
		return counter;
	}
	
	/**
	 * Метод считает разность максимального и минимального целочисленных элементов массова
	 * @param array - не пустой массив целочисленных элементов
	 * @return - int difference
	 */
	
	private static int getMaxMinDifference(int[] array) {
		if (array.length == 0 || array.length == 1) {
			return 0;
		}
		int max = array[0], min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			} else if (array[i] < min) {
				min = array[i];
			}
		}
		return max - min;
		
	}
	
	/**
	 * Метод проверяет имеются ли в массиве 2 соседних элемента int = 0
	 * @param array - переданный массив целочисленных элементов
	 * @return - true or false
	 */
	private static boolean isDoubleZero(int[] array) {
		if (array.length == 0 || array.length == 1) {
			return false;
		} else if (array.length <= 270) {
			for (int i = 1; i < array.length - 1; i++) {
				if (array[i] == 0) {
					if (array[i + 1] == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}
}










/*
ЛИБО

Задания по доработке игры:

Переписать алгоритм проверки победы (метод checkWin())
(*) Расширить логику игры для квадратного поля произвольного размера (достаточно для 5Х5)
(**) сделать более интеллектуальные ходы компьютера
Иные доработки на ваше усмотрение.
 */
class Game {
	//private static final int WIN_COUNT = 3;
	private static final char DOT_HUMAN = 'X';
	private static final char DOT_AI = 'O';
	private static final char DOT_EMPTY = '.';
	
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final Random RANDOM = new Random();
	
	private static char[][] field;
	private static final int SIZE_X = 3;
	private static final int SIZE_Y = 3;

//	public static void main(String[] args) {
//		initialize();
//		printField();
//		while(true) {
//			humanTurn();
//			printField();
//			if (gameCheck(DOT_HUMAN, "You won!"))
//				break;
//
//			aiTurn();
//			printField();
//			if (gameCheck(DOT_AI, "Computer won!"))
//				break;
//		}
//	}
	
	private static void initialize() {
		field = new char[SIZE_X][SIZE_Y];
		for (int x = 0; x < SIZE_X; x++) {
			for (int y = 0; y < SIZE_Y; y++) {
				field[x][y] = DOT_EMPTY;
			}
		}
	}
	
	private static void printField() {
		System.out.print("+");
		for (int i = 0; i < SIZE_X * 2 + 1; i++) {
			System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
		}
		System.out.println();
		
		for (int i = 0; i < SIZE_X; i++) {
			System.out.print(i + 1 + "|");
			
			for (int j = 0; j < SIZE_Y; j++)
				System.out.print(field[i][j] + "|");
			
			System.out.println();
		}
		
		for (int i = 0; i < SIZE_X * 2 + 2; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private static void humanTurn() {
		int x, y;
		do {
			System.out.println("Enter the coordinates Х и Y  (1 to 3) space separated: ");
			x = SCANNER.nextInt() - 1;
			y = SCANNER.nextInt() - 1;
		} while (!isCellValid(x, y) || !isCellEmpty(x, y));
		field[x][y] = DOT_HUMAN;
	}
	
	private static boolean isCellEmpty(int x, int y) {
		return field[x][y] == DOT_EMPTY;
	}
	
	private static boolean isCellValid(int x, int y) {
		return x >= 0 && x < SIZE_X
				&& y >= 0 && y < SIZE_Y;
	}
	
	private static void aiTurn() {
		int x, y;
		do {
			x = RANDOM.nextInt(SIZE_X);
			y = RANDOM.nextInt(SIZE_Y);
		} while (!isCellEmpty(x, y));
		field[x][y] = DOT_AI;
	}
	
	private static boolean gameCheck(char symbol, String message) {
		if (checkWin(symbol)) {
			System.out.println(message);
			return true;
		}
		if (checkDraw()) {
			System.out.println("Draw");
			return true;
		}
		return false;
	}
	
	private static boolean checkDraw() {
		for (int x = 0; x < SIZE_X; x++) {
			for (int y = 0; y < SIZE_Y; y++) {
				if (isCellEmpty(x, y)) return false;
			}
		}
		
		return true;
	}
	
	private static boolean checkWin(char symbol) {
		// Проверка по трем горизонталям
		if (field[0][0] == symbol && field[0][1] == symbol && field[0][2] == symbol) return true;
		if (field[1][0] == symbol && field[1][1] == symbol && field[1][2] == symbol) return true;
		if (field[2][0] == symbol && field[2][1] == symbol && field[2][2] == symbol) return true;
		
		// Проверка по диагоналям
		if (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol) return true;
		if (field[0][2] == symbol && field[1][1] == symbol && field[2][0] == symbol) return true;
		
		// Проверка по трем вертикалям
		if (field[0][0] == symbol && field[1][0] == symbol && field[2][0] == symbol) return true;
		if (field[0][1] == symbol && field[1][1] == symbol && field[2][1] == symbol) return true;
		if (field[0][2] == symbol && field[1][2] == symbol && field[2][2] == symbol) return true;
		
		return false;
	}
}
