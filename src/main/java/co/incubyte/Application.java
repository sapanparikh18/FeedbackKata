package co.incubyte;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        ApplicationInsights.attach();
        Micronaut.run(Application.class, args);
    }
}