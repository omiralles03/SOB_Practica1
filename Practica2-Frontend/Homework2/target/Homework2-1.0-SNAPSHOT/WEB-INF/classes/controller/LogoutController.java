package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.UriRef;
import jakarta.mvc.security.CsrfProtected;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("Logout")
@Controller
public class LogoutController {
    @Inject Logger log;
    
    @POST
    @UriRef("logout")
    @CsrfProtected
    public String handleLogout(@Context HttpServletRequest request) {
        log.log(Level.INFO, "LOGUT BUTTTON ACTIVVEE");
        request.getSession().invalidate(); // Clear the session
        return "redirect:/Home";
    }
}
