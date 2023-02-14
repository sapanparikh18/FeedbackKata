package co.incubyte;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;

@Singleton
public class ServerEventListener {
    @EventListener
    public void onStartup(ServerStartupEvent event){
        ApplicationInsights.attach();
    }
}
