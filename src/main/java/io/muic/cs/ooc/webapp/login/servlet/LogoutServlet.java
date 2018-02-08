package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet implements Routeable {

    private MySQL mySQL;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
        rd.include(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean passed = mySQL.authenticate(username, password);

        request.getSession().removeAttribute("invalidLogin");
        request.getSession().removeAttribute("emptyField");

        if(!StringUtils.isBlank(username) && !StringUtils.isBlank(password)){
            if(passed){
                System.out.println("Authenticate successful");
                User user = mySQL.getUserbyUsername(username);
                request.getSession().setAttribute("LOGIN_USER", user);
                request.getSession().removeAttribute("error");
                response.sendRedirect("/user");
            }else{
                request.getSession().setAttribute("invalidLogin",true);
                response.sendRedirect("/login");
            }
        }else{
            request.getSession().setAttribute("emptyField",true);
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getMapping() {
        return "/login";
    }

    @Override
    public void setMySQL(MySQL mySQL) {
        this.mySQL = mySQL;
    }
}
