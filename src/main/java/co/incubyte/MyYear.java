package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyYear {
    private final int year;
    private boolean leap;

    public void setLeap(boolean leap) {
        this.leap = leap;
    }
    public MyYear(@JsonProperty("year") int year) {
        this.year = year;
    }
    public boolean isLeap() {
        return leap;
    }

}