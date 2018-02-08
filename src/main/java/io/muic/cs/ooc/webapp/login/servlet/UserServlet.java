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

public class UserServlet extends HttpServlet implements Routeable {

    private MySQL mySQL;

    @Override
    public String getMapping() {
        return "/user";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rq = req.getRequestDispatcher("WEB-INF/user.jsp");
        rq.include(req,resp);
    }

    @Override
    public void setMySQL(MySQL mySQL) {
        this.mySQL = mySQL;
    }
}
