package authn;

import com.sun.xml.messaging.saaj.util.Base64;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import jakarta.annotation.Priority;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.container.ResourceInfo;


/**
 * @author Marc Sanchez
 */
@Priority(Priorities.AUTHENTICATION)
@Provider
public class RESTRequestFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";      
    
    // to access the resource class and resource method matched by the current request
    @Context
    private ResourceInfo resourceInfo;

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    @Override
    public void filter(ContainerRequestContext requestCtx) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        if (method != null) 
        {
            Secured secured = method.getAnnotation(Secured.class);
            if(secured != null)
            {
                
                List<String> headers = requestCtx.getHeaders()
                        .get(HttpHeaders.AUTHORIZATION);
                
                if(headers != null && !headers.isEmpty())
                {
                    String username;
                    String password;
                    try {
                        String auth = headers.get(0);
                        auth = auth.replace(AUTHORIZATION_HEADER_PREFIX, "");
                        String decode = Base64.base64Decode(auth);
                        StringTokenizer tokenizer = new StringTokenizer(decode, ":");
                        username = tokenizer.nextToken();
                        password = tokenizer.nextToken();
                    } catch(@SuppressWarnings("unused") Exception e){
                        requestCtx.abortWith(
                                Response.status(Response.Status.BAD_REQUEST).build()
                        );
                        return;
                    }
                    
                    try {
                        TypedQuery<Credentials> query = em.createNamedQuery("Credentials.findUser", Credentials.class);
                        Credentials c = query.setParameter("username", username)
                            .getSingleResult();
                        if(!c.getPassword().equals(password)) {
                            requestCtx.abortWith(
                                Response.status(Response.Status.FORBIDDEN).build()
                            );
                        }
                    } catch(@SuppressWarnings("unused") NoResultException e) {
                        requestCtx.abortWith(
                            Response.status(Response.Status.UNAUTHORIZED).build()
                        );
                    }                  
                }  
                else {
                   requestCtx.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).build()
                    );
                }
            }
        }
    }
}