package co.incubyte;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/year")
public class YearController {
    @Get("/{year}")
    public Year isLeap(int year) {
        Year responseYear = new Year(year);
        if (year % 400 == 0) {
            responseYear.setLeap(true);
            return responseYear;
        }
        if (year % 100 == 0 && !(year % 400 == 0)) {
            responseYear.setLeap(false);
            return responseYear;
        }
        if (year % 4 == 0) {
            responseYear.setLeap(true);
            return responseYear;
        }
        return responseYear;
    }
}

