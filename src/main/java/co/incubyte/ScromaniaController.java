package co.incubyte;

import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/scromania")
public class ScromaniaController {

  private static final Logger log = LoggerFactory.getLogger(ScromaniaController.class);

    @Get(uri = "/", produces = "text/plain")
    public String index() {
        log.info("requesting a resource");
        return "Example Response";
    }
}
