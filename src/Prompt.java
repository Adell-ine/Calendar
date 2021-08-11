import java.util.Scanner;

public class Prompt {

	private final static String PROMPT = "cal> "; // 변경 안되는것을 private final로 쓰고 대문자로 지정함

	public void runPrompt() {

		Scanner scanner = new Scanner(System.in);
		calendar cal = new calendar();

		int month = -1;
		int year = -1;
		while (true) {
		
			System.out.println("년도를 입력하세요.");
			System.out.print("YEAR> ");
			year = scanner.nextInt();
			System.out.println("최대 일수를 알고싶은 달을 입력하세요.");
			System.out.print("MONTH> ");
			month = scanner.nextInt();
			if (month == -1) {
				break; // 만나면 루프를 빠져나감
			}

			if (month > 12) {
				continue; // 만나면 루프의 처음으로 돌아감
			}
			cal.printCalendar(year, month);
		}

		System.out.println("끗~");
		scanner.close();
	}

	public static void main(String[] args) {
		// 셀 실행
		Prompt p = new Prompt();
		p.runPrompt();
	}

}
