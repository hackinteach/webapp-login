package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet implements Routeable {

    private MySQL mySQL;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
        rd.include(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean passed = mySQL.authenticate(username, password);
        request.removeAttribute("error");

        if(username == null || password == null){
            request.setAttribute("error",true);
        }

        if(passed){
            System.out.println("Authenticate successful");
            User user = mySQL.getUserbyUsername(username);
            request.getSession().setAttribute("user", user);
            request.getSession().removeAttribute("error");
            response.sendRedirect("/user");
        }
        else{
            request.getSession().setAttribute("error",true);
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getMapping() {
        return "/login";
    }

    @Override
    public void setMySQL(MySQL mySQL) {
        this.mySQL = mySQL;
    }
}
