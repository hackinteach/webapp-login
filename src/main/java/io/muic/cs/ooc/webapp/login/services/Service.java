package io.muic.cs.ooc.webapp.login.services;

import io.muic.cs.ooc.webapp.login.database.MySQL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Service {

    protected MySQL mySQL = new MySQL();

    public void error(HttpServletRequest request, HttpServletResponse response, String msg, String path) {
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
