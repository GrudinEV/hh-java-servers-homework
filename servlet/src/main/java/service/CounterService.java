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

    public int substructionCount(int subtractionValue) {
        int factSubstractionValue = Math.min(subtractionValue, count);
        count -= factSubstractionValue;
        return factSubstractionValue;
    }

    public void clearCounter() {
        count = 0;
    }
}
