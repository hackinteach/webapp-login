package io.muic.cs.ooc.webapp.login.services;

import io.muic.cs.ooc.webapp.login.database.MySQL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginService{

    private MySQL mySQL = new MySQL();
    private HttpServletRequest request;
    private HttpServletResponse response;

    public LoginService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

    }

    public boolean login(String username, String password){
        return mySQL.authenticate(username,password);
    }

    public void error(String msg, String path){
        try {
            System.out.println(msg);
            request.setAttribute("error",msg);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
            requestDispatcher.include(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
