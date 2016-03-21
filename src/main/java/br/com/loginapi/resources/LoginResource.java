package br.com.loginapi.resources;

import br.com.loginapi.core.Login;
import br.com.loginapi.db.LoginDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    private final LoginDAO peopleDAO;

    public LoginResource(LoginDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @POST
    @UnitOfWork
    public Login createPerson(Login person) {
        return peopleDAO.create(person);
    }

    @GET
    @UnitOfWork
    public List<Login> listPeople() {
        return peopleDAO.findAll();
    }

}
