package co.incubyte;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import java.time.ZonedDateTime;

@Singleton
public class ServerEventListener {

  private ZonedDateTime startedAt;

  @EventListener
  public void onStartup(ServerStartupEvent event) {
    startedAt = ZonedDateTime.now();
  }

  public ZonedDateTime getStartedAt() {
    return startedAt;
  }
}
