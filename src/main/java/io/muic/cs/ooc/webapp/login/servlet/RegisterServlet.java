package io.muic.cs.ooc.webapp.servlet;

import io.muic.cs.ooc.webapp.Routeable;
import io.muic.cs.ooc.webapp.service.RegisterService;
import io.muic.cs.ooc.webapp.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet implements Routeable {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
//
//        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
//        rd.include(request,response);

        RegisterService registerService = new RegisterService();
        boolean result = registerService.register(user);
        if(result){
            response.sendRedirect("/user.jsp");
        }else{
            response.sendRedirect("/register-fail.jsp");
        }
    }

    @Override
    public String getMapping() {
        return "/register";
    }

}
