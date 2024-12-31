package services;

import java.util.Set;
import jakarta.ws.rs.core.Application;

@jakarta.ws.rs.ApplicationPath("rest/api")
public class ApplicationConfig extends Application {
  @Override
  public Set<Object> getSingletons() {
      Set<Object> set = new java.util.HashSet<>();
      set.add(new services.UserResource());
      return set;
  }  
}
