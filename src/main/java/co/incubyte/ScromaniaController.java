package co.incubyte;

import io.micronaut.http.annotation.*;

@Controller("/scromania")
public class ScromaniaController {

    @Get(uri = "/", produces = "text/plain")
    public String index() {
        String value = null;
        if(value.equals("this")){
            System.out.println("that");
        }
        return "Example Response";
    }
}