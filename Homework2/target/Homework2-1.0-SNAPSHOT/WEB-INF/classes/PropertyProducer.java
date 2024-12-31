package deim.urv.cat.homework2;

import deim.urv.cat.homework2.exception.PropertyException;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyProducer {
    @Inject Logger log;

    @Property
    @Produces
    public String readProperty(InjectionPoint point) {
        String key = point
                .getAnnotated()
                .getAnnotation(Property.class)
                .key();
        
        log.log(Level.INFO, "Reading property {0}", key);
     
        return readFromPath(point
                .getAnnotated()
                .getAnnotation(Property.class)
                .fileName(), key);
    }

    private String readFromPath(String fileName, String key) {
        try (InputStream stream = PropertyProducer.class
                .getResourceAsStream("/" + fileName) ) {

            Properties properties = new Properties();
            properties.load(stream);

            return properties.getProperty(key);
        } catch (Exception e ) {
            log.log(Level.SEVERE, "Error to read property {0}", key);
            throw new PropertyException("Error to read property ".concat(key));
        }
    }
}

