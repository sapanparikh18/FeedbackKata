package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Year {
    private final int year;
    private boolean leap;

    public void setLeap(boolean leap) {
        this.leap = leap;
    }
    public Year(@JsonProperty("year") int year) {
        this.year = year;
    }
    public boolean isLeap() {
        return leap;
    }

}