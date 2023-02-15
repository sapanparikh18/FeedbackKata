package co.incubyte;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class YearControllerShould {
    @Test
    @DisplayName("should return leap if year is divisible by 400")
    public void should_return_leap_if_year_is_divisible_by_400() {
        YearController yearController = new YearController();
        Year year = yearController.isLeap(4000);
        Assertions.assertThat(year.isLeap()).isTrue();
    }

    @Test
    @DisplayName("not leap if divisible by 100 but not by 400")
    public void not_leap_if_divisible_by_100_but_not_by_400() {
        YearController yearController = new YearController();
        Year year = yearController.isLeap(1800);
        Assertions.assertThat(year.isLeap()).isFalse();
    }
    @Test
    @DisplayName("divisible by 4 but not by 100 are leap years")
    public void divisible_by_4_but_not_by_100_are_leap_years() {
        YearController yearController = new YearController();
        Year year = yearController.isLeap(2016);
        Assertions.assertThat(year.isLeap()).isTrue();;
    }
}
