import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();
        StepTracker tracker = new StepTracker(); // !��������� ������, ���� ������ �����

        while (isAppRun(userInput)) {
            switch (userInput) {
                case 1: tracker.inputStepCount(); break;
                case 2: tracker.showMonthStatistic(); break;
                case 3: tracker.changeTargetInDay(); break;
                default: System.out.println("�������� �������. ��������� ����.");
            }

            printMenu();
            userInput = scanner.nextInt();
        }

        System.out.println("��������� ���������");
    }

    public static void printMenu(){
        System.out.println("1. ������ ���������� ����� �� ������������ ����");
        System.out.println("2. ���������� ���������� �� ������������ �����");
        System.out.println("3. �������� ���� �� ���������� ����� � ����");
        System.out.println("4. ����� �� ����������");
    }

    public static boolean isAppRun(int value) {
        return (value != 4);
    }

}