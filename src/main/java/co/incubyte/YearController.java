package co.incubyte;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("year")
public class YearController {
    @Get("/{year}")
    public Year isLeap(int year) {
        Year responseYear = new Year(year);
        return responseYear;
    }

}
