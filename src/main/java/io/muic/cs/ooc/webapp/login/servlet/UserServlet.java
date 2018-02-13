package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.model.User;
import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.services.UserService;
import io.muic.cs.ooc.webapp.login.utils.CookieUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet implements Routeable {

    @Override
    public String getMapping() {
        return "/user";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!CookieUtil.verifyCookie(req,resp)){
            resp.sendRedirect("/login");
        }else{
            UserService userService = new UserService();
            User user = CookieUtil.getUser(req,resp);
            req.setAttribute("user",user);
            RequestDispatcher rq = req.getRequestDispatcher("WEB-INF/user.jsp");
            rq.include(req,resp);
        }
    }
}
