package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.Routeable;
import io.muic.cs.ooc.webapp.service.LoginService;
import io.muic.cs.ooc.webapp.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet implements Routeable {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
        rd.include(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginService loginService = new LoginService();
        boolean result = loginService.authenticateUser(username, password);
        User user = loginService.getUserByUsername(username);
        request.removeAttribute("error");
        if(result){
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
}
