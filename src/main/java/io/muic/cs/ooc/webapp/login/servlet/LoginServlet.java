package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;
import io.muic.cs.ooc.webapp.login.services.LoginService;
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
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginService lis = new LoginService(request,response);

        if(!StringUtils.isBlank(username) && !StringUtils.isBlank(password)){
            boolean passed = lis.login(username,password);
            if(passed){
                System.out.println("Authenticate successful");
                User user = mySQL.getUserbyUsername(username);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("/user");
            }else{
                String error = "Invalid Login";
                lis.error(error, "WEB-INF/login.jsp");
            }
        }else{
            String error = "Username and Password cannot be blank";
            lis.error(error,"WEB-INF/login.jsp");
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
