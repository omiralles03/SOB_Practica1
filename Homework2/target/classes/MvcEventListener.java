package deim.urv.cat.homework2;

import java.util.logging.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.mvc.event.AfterControllerEvent;
import jakarta.mvc.event.AfterProcessViewEvent;
import jakarta.mvc.event.BeforeControllerEvent;
import jakarta.mvc.event.BeforeProcessViewEvent;
import jakarta.mvc.event.ControllerRedirectEvent;

@ApplicationScoped
public class MvcEventListener {

    @Inject
    Logger log;

    private void onControllerMatched(@Observes BeforeControllerEvent event) {
        log.info(() -> "Controller for " + event.getUriInfo().getRequestUri());
    }

    private void onViewEngineSelected(@Observes BeforeProcessViewEvent event) {
        log.info(() -> "View engine: " + event.getEngine());
    }

    private void onAfterControllerEvent(@Observes AfterControllerEvent event) {
        log.info(() -> "AfterControllerEvent:: " + event.getResourceInfo());
    }

    private void onAfterProcessViewEvent(@Observes AfterProcessViewEvent event) {
        log.info(() -> "AfterProcessViewEvent:: " + event);
    }
    
     private void onControllerRedirectEvent(@Observes ControllerRedirectEvent event) {
        log.info(() -> "ControllerRedirectEvent:: " + event);
    }

    @PostConstruct
    private void init() {
        log.config(() -> this.getClass().getSimpleName() + " created");
    }
}
