package io.muic.cs.ooc.webapp.login.services;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterService extends Service{

    private HttpServletResponse response;
    private HttpServletRequest request;

    public RegisterService(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    public boolean register(String username,
                            String password,
                            String firstname,
                            String lastname,
                            String email){
        return MySQL.createUser(username,password,firstname,lastname,email);
    }

    public boolean dupUser(String username){
        return MySQL.isUserExists(username);
    }

    public User getUser(String username){
        return MySQL.getUserbyUsername(username);
    }

    public boolean dupEmail(String email) {
        return MySQL.isEmailExists(email);
    }
}
