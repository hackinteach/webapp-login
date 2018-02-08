package io.muic.cs.ooc.webapp.login.servlet;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.muic.cs.ooc.webapp.login.Routeable;
import io.muic.cs.ooc.webapp.service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author hackinteach
 */
public class HomeServlet extends HttpServlet implements Routeable {

    @Override
    public String getMapping() {
        return "/index.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService loginService = new LoginService();

//        for(User u : loginService.getListOfUsers()){
//            response.getWriter().append(u.getUsername()).append(u.getPassword());
//        }
        boolean authenticated = loginService.isAuthorized();
        response.getWriter().append(Boolean.toString(authenticated));
        if(authenticated){
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
            rd.include(request,response);
        }else{
            response.sendRedirect("/login");
        }
    }
}
