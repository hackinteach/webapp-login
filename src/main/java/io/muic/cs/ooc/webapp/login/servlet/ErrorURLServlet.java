package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error_handler")
public class ErrorURLServlet extends HttpServlet implements Routeable {
    @Override
    public String getMapping() {
        return "/error_handler";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(CookieUtil.verifyCookie(req,resp)){
            resp.sendRedirect("/user");
        }else{
            resp.sendRedirect("/login");
        }
    }
}
