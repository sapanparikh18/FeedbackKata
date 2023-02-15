package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Year {
    private final int inputYear;
    private boolean leap;

    public void setLeap(boolean leap) {
        this.leap = leap;
    }
    public Year(@JsonProperty("year") int year) {
        this.inputYear = year;
    }
    public boolean isLeap() {
        return leap;
    }

    public int getInputYear() {
        return inputYear;
    }
}