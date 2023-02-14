package co.incubyte;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import io.micronaut.runtime.Micronaut;

public class Application {
    static {
        ApplicationInsights.attach();
    }
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}