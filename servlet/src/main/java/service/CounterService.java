package service;

public class CounterService {
    public static final CounterService SERVICE = new CounterService();
    private int count = 0;

    private CounterService() {}

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    public int subtractionCount(int subtractionValue) {
        int factSubtractionValue = Math.min(subtractionValue, count);
        count -= factSubtractionValue;
        return factSubtractionValue;
    }

    public void clearCounter() {
        count = 0;
    }
}
