package service;

import dto.DataCounterDto;

import java.sql.Timestamp;

public class JerseyService {
    public static final JerseyService SERVICE = new JerseyService();
    private final CounterService service = CounterService.SERVICE;

    private JerseyService() {
    }

    public DataCounterDto getDataCounter() {

        return new DataCounterDto(
                new Timestamp(System.nanoTime()).toLocalDateTime(),
                service.getCount()
        );
    }

    public void incrementCount() {
        service.incrementCount();
    }

    public int subtractionCount(int subtractionValue) {
        return service.subtractionCount(subtractionValue);
    }

    public void clearCounter() {
        service.clearCounter();
    }
}
