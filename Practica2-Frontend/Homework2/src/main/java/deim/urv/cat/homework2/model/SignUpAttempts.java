package deim.urv.cat.homework2.model;

import deim.urv.cat.homework2.Config;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;

import java.util.logging.Logger;

@SessionScoped
@Named("attempts")
public class SignUpAttempts implements Serializable {
    // Transient to ensure this managed bean is passivation capable
    @Inject transient Config config;
    @Inject transient Logger log;
    
    private int MAX_ATTEMPTS = 10;
    private int number = 0;
    
    @PostConstruct
    public void init() {
        try {
            MAX_ATTEMPTS = Integer.parseInt(config.MAX_ATTEMPTS);
            log.log(Level.INFO, "Maximnum number of attempts: {0}", 
                    MAX_ATTEMPTS);
        }
        catch (NumberFormatException ex){ }
    }
            
    public int getNumber() {
        return number;
    }
    
    public void increment() {
        number++;
    }
    
    public void reset() {
        number = 0;
    }
    
    public boolean hasExceededMaxAttempts() {
        return number > MAX_ATTEMPTS;
    }
}
