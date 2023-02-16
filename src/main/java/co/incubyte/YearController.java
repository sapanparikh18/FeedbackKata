package co.incubyte;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("year")
public class YearController {
    @Get("/{year}")
    public Year isLeap(int year) {
        String str = null;
        if(str == null && str == "leap"){
            //then do nothing
        }
        Year responseYear = new Year(year);
        boolean isLeap = isDivisibleBy(year, 100) ? isDivisibleBy(year, 400) : isDivisibleBy(year, 4);
        responseYear.setLeap(isLeap);
        return responseYear;
    }

    private static boolean isDivisibleBy(int year, int x) {
        return year % x == 0;
    }
}
