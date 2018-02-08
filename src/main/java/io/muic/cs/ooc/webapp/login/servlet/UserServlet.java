package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;
import io.muic.cs.ooc.webapp.login.router.Routeable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet implements Routeable {

    private MySQL mySQL;

    @Override
    public String getMapping() {
        return "/user";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rq = req.getRequestDispatcher("WEB-INF/user.jsp");
        User user =  (User)req.getSession().getAttribute("user");
        PrintWriter writer  = resp.getWriter();
        writer.append("Hi, "+user.getUsername());
        writer.flush();
        writer.close();
    }

    @Override
    public void setMySQL(MySQL mySQL) {
        this.mySQL = mySQL;
    }
}
