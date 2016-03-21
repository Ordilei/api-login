package br.com.loginapi.db;

import com.google.common.base.Optional;

import br.com.loginapi.core.Login;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class LoginDAO extends AbstractDAO<Login> {
    public LoginDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Login> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Login create(Login person) {
        return persist(person);
    }

    public List<Login> findAll() {
        return list(namedQuery("com.example.helloworld.core.Person.findAll"));
    }
}
