package dto;

import java.time.LocalDateTime;

public class DataCounterDto {
    private LocalDateTime date;
    private int value;

    public DataCounterDto(LocalDateTime date, int value) {
        this.date = date;
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
