public class Converter {
    double oneStepDistance = 0.00075;
    double oneStepKCalories = 0.05;

    public double convertDistance(int step) {
        return step * oneStepDistance;
    }

    public double convertKCalories(int step) {
        return step * oneStepKCalories;
    }
}
