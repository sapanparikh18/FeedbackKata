package co.incubyte;

import io.micronaut.http.annotation.*;

@Controller("/scromania")
public class ScromaniaController {

    @Get(uri = "/", produces = "text/plain")
    public String index() {
        return "Example Response";
    }
}