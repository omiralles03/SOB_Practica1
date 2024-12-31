package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.controller.UserForm;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.client.Entity;
        
public class UserServiceImpl implements UserService {
    private final WebTarget webTarget;
    private final jakarta.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/UserService/rest/api";
    
    public UserServiceImpl() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("user");
    }
    
    @Override
    public User findUserByEmail(String email){
        Response response = webTarget.path(email)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(User.class);
        }
        return null;
    }

    @Override
    public boolean addUser(UserForm user) {
       Response response = webTarget.request(MediaType.APPLICATION_JSON)
               .post(Entity.entity(user, MediaType.APPLICATION_JSON), 
                    Response.class);
     return response.getStatus() == 201;
    }

}
