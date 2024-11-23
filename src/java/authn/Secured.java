package authn;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jakarta.inject.Qualifier;

/**
 * @author Marc Sanchez
 */
@Qualifier
@Retention(RUNTIME)
@Target(METHOD)
public @interface Secured {
}
