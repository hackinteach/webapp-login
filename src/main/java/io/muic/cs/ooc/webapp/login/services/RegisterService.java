package io.muic.cs.ooc.webapp.login.services;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterService extends Service{

    private HttpServletResponse response;
    private HttpServletRequest request;

    public RegisterService(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    public boolean register(String username, String password){
        return mySQL.createUser(username,password);
    }

    public boolean dupUser(String username){
        return mySQL.isUserExists(username);
    }

    public User getUser(String username){
        return mySQL.getUserbyUsername(username);
    }
}
