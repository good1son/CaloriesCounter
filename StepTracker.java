import java.util.HashMap;
import java.util.Scanner;

public class StepTracker {

    HashMap<Integer, MonthData> monthToData = new HashMap<Integer, MonthData>();
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int targetInDay = 10000;
    Scanner scanner = new Scanner(System.in);

    public int getTarget() {
        return targetInDay;
    }

    public StepTracker() {
        for (int i = 0; i < 12; i++)
            monthToData.put(i, new MonthData(daysInMonth[i]));
    }

    public void inputStepCount() {
        System.out.println("Введите номер месяца: ");
        int month = checkMonth(scanner.nextInt());

        System.out.println("Введите день: ");
        int day = checkDay(scanner.nextInt(), month - 1);

        System.out.println("Введите количество шагов за " + day + "й день " + month + " месяца: ");
        int steps = checkSteps(scanner.nextInt());
        monthToData.get(month - 1).steps[day - 1] = steps;
    }

    public void showMonthStatistic() {
        System.out.println("Введите номер месяца: ");
        int month = scanner.nextInt();
        month = checkMonth(month);
        System.out.println("Статистика шагов за "  + month + " месяц:");
        monthToData.get(month - 1).getMonthStatistic();
    }

    public void changeTargetInDay() {
        System.out.println("Введите новую цель по количеству шагов в день: ");
        targetInDay = checkTarget(scanner.nextInt());
    }

    public int checkMonth(int month) {
        while (month < 1 || month > 12) {
            System.out.println("В году 12 месяцев. Введите номер месяца в диапазоне 1 - 12");
            month = scanner.nextInt();
        }
        return month;
    }

    public int checkDay(int day, int month) {
        while (day < 1 || day > daysInMonth[month]) {
            System.out.println("В " + (month + 1) + " месяце " + daysInMonth[month] + " дней. Введите день в диапазоне 1 - " + daysInMonth[month]);
            day = scanner.nextInt();
        }
        return day;
    }

    public int checkSteps(int steps) {
        while (steps < 0 || steps > 400000) {
            if (steps < 0) System.out.println("Количество шагов не может быть отрицательным. Повторите ввод");
            if (steps > 400000) System.out.println("Вы супермен, раз сделали 400 000+ шагов за сутки. Повторите ввод");
            steps = scanner.nextInt();
        }
        return steps;
    }

    public int checkTarget(int steps) {
        while (steps < 0 ) {
            System.out.println("Целевое количество шагов не может быть отрицательным. Повторите ввод");
            steps = scanner.nextInt();
        }
        return steps;
    }


    class MonthData {
        int[] steps;
        int stepsPerMonth;
        int maxSteps;
        int averageSteps;
        int bestSeriesSteps;

        MonthData(int days) {
            steps = new int [days];
        }

        Converter converter = new Converter();
        double distance;
        double kCalories;


        public int totalStepsPerMonth() {
            for (int step: steps)
                stepsPerMonth += step;
            return stepsPerMonth;
        }

        public int maxStepsPerMonth() {
            for (int step: steps)
                if (step > maxSteps)
                    maxSteps = step;
            return maxSteps;
        }

        public int averageStepsPerMonth() {
            averageSteps = stepsPerMonth / steps.length;
            return averageSteps;
        }

        public int bestSeriesPerMonth() {
            int tempSeries = 0;
            for (int step : steps) {
                if (step >= targetInDay)
                    tempSeries ++;
                else {
                    if (tempSeries > bestSeriesSteps)
                        bestSeriesSteps = tempSeries;
                    tempSeries = 0;
                }
            }
            return bestSeriesSteps;
        }

        public double getDistance() {
            distance = converter.convertDistance(stepsPerMonth);
            return distance;
        }

        public double getBurnKCalories() {
            kCalories = converter.convertKCalories(stepsPerMonth);
            return kCalories;
        }

        public void getMonthStatistic() {
            System.out.println("Статистика шагов по дням:  ");
            for (int day = 0; day < steps.length; day++)
                System.out.println(day + 1 + "й день: " + steps[day] + " шагов");
            System.out.println("Суммарное количество шагов: " + totalStepsPerMonth());
            System.out.println("Среднее количество шагов за день: " + averageStepsPerMonth());
            System.out.println("Максимальное количество шагов за день: " + maxStepsPerMonth());
            System.out.println("Пройденная дистанция " + getDistance() + " км.");
            System.out.println("Сожжено ККалорий: " + getBurnKCalories());
            System.out.println("Лучшая серия выполнения целевых шагов (" + targetInDay + ") " + bestSeriesPerMonth());
        }
    }

}
