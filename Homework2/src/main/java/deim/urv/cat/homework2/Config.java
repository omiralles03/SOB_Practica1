package deim.urv.cat.homework2;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class Config {
    @Inject
    @Property(key="max.attempts")
    public String MAX_ATTEMPTS;
}
