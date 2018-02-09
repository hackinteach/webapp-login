package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;
import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.services.RegisterService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet implements Routeable {

    private MySQL mySQL;

    @Override
    public String getMapping() {
        return "/register";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rq = req.getRequestDispatcher("WEB-INF/register.jsp");
        rq.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        RegisterService registerService = new RegisterService(req,resp);

        if(!StringUtils.isBlank(username) && !StringUtils.isBlank(password)){
            if(registerService.dupUser(username)){
                String error = "Username already taken. Please try again.";
                registerService.error(error,"WEB-INF/register.jsp");
            }else{
                if(mySQL.createUser(username,password)){
                    User user = mySQL.getUserbyUsername(username);
                    req.getSession().setAttribute("user",user);
                    resp.sendRedirect("/user");
                }
            }
        }else{
            String error = "cannot create blank user";
            registerService.error(error,"WEB-INF/register.jsp");
        }
    }

    @Override
    public void setMySQL(MySQL mySQL) {
        this.mySQL = mySQL;
    }
}
