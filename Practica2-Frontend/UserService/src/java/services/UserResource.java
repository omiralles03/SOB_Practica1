package services;

import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.User;
import model.EmailValidator;

@Path("/user")
public class UserResource {
    
    private static List<User> users = new ArrayList<User>();
    private static int count = 2;
    static {
	users.add(new User("oh", "no!", "oh.no@urv.cat"));
	users.add(new User("john", "doe", "john.doe@urv.cat"));
    }

    @GET
    @Path("{email}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response GetUserByEmail(@PathParam("email") String email){
        if (!EmailValidator.isValid(email))
            return Response.status(Response.Status.BAD_REQUEST).build();
        User user = users.stream()
            .filter(_user -> email.equals(_user.getEmail()))
            .findAny()
            .orElse(null);
        if (user == null) 
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user).build();
    }
	
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addUser(User user) {
        if (findUserByEmail(user) == null) {
            users.add(user); 
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    private User findUserByEmail(User user){
        String email = user.getEmail();
	return users.stream()
            .filter(_user -> email.equals(_user.getEmail()))
            .findAny()
            .orElse(null);
    }
}
