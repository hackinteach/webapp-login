package io.muic.cs.ooc.webapp.login.servlet;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.utils.CookieUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hackinteach
 */

@WebServlet("/")
public class HomeServlet extends HttpServlet implements Routeable {

    @Override
    public String getMapping() {
        return "/index.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = CookieUtil.verifyCookie(request,response);
        if(authorized){
            response.sendRedirect("/user");
        }else{
            response.sendRedirect("/login");
        }
    }
}
