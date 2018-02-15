package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;
import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.services.LoginService;
import io.muic.cs.ooc.webapp.login.utils.CookieUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet implements Routeable {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CookieUtil.verifyCookie(request, response)) {
            response.sendRedirect("/user");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginService lis = new LoginService(request, response);

        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            boolean passed = lis.login(username, password);
            if (passed) {
                System.out.println("Authenticate successful");
                User user = MySQL.getUserbyUsername(username);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("/user");
            } else {
                String error = "Invalid Login";
                lis.error(request, response, error, "WEB-INF/login.jsp");
            }
        } else {
            String error = "Username and Password cannot be blank";
            lis.error(request, response, error, "WEB-INF/login.jsp");
        }
    }

    @Override
    public String getMapping() {
        return "/login";
    }
}
