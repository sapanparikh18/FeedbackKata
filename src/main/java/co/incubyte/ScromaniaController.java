package co.incubyte;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/scromania")
public class ScromaniaController {

  private static final Logger log = LoggerFactory.getLogger(ScromaniaController.class);
  private Long requestCount = 0L;

  private final ServerEventListener serverEventListener;

  public ScromaniaController(ServerEventListener serverEventListener) {
    this.serverEventListener = serverEventListener;
  }

  @Get(uri = "/", produces = "text/plain")
  public String index() {
    log.info("requesting a resource");
    return "Example Response 0.2 at " + serverEventListener.getStartedAt() + " with " + (++requestCount) + " request count";
  }
}
