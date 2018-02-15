package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;
import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveUserServlet extends HttpServlet implements Routeable {
    @Override
    public String getMapping() {
        return "/remove";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!CookieUtil.verifyCookie(req,resp)) {
            resp.sendRedirect("/login");
            return;
        }
//        @TODO Prevent backend remove.
        String username = req.getParameter("username");
//        User curr = (User)req.getSession().getAttribute("user");
        User curr = CookieUtil.getUser(req,resp);
        if(!username.equals(curr.getUsername())){
            MySQL.removeUserbyUsername(username);
        }
        resp.sendRedirect("/user");

    }
}
