import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();
        StepTracker tracker = new StepTracker(); // !создастся обьект, хотя нажали выход

        while (isAppRun(userInput)) {
            switch (userInput) {
                case 1: tracker.inputStepCount(); break;
                case 2: tracker.showMonthStatistic(); break;
                case 3: tracker.changeTargetInDay(); break;
                default: System.out.println("Неверная команда. Повторите ввод.");
            }

            printMenu();
            userInput = scanner.nextInt();
        }

        System.out.println("Программа завершена");
    }

    public static void printMenu(){
        System.out.println("1. Ввести количество шагов за определенный день");
        System.out.println("2. Напечатать статистику за определенный месяц");
        System.out.println("3. Изменить цель по количеству шагов в день");
        System.out.println("4. Выйти из приложения");
    }

    public static boolean isAppRun(int value) {
        return (value != 4);
    }

}