package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;
import io.muic.cs.ooc.webapp.login.router.Routeable;
import io.muic.cs.ooc.webapp.login.services.UpdateService;
import io.muic.cs.ooc.webapp.login.utils.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.sql.Update;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet implements Routeable {

    private String userToEdit;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userToEdit = request.getParameter("editUser");
        if(StringUtils.isBlank(userToEdit)){
            response.sendRedirect("/user");
        }
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String new_pwd = request.getParameter("new_password");
        String confirm_new_pwd = request.getParameter("cf_password");

        UpdateService updateService = new UpdateService();
//        System.out.println(firstname + lastname + email+new_pwd+confirm_new_pwd+username);
        Map<String,String> newProfile = new HashMap<>();
        if(!StringUtils.isBlank(username)) {
            newProfile.put("username", username);
        }
        if(!StringUtils.isBlank(email)){
            newProfile.put("email",email);
        }
        if(!StringUtils.isBlank(lastname)){
            newProfile.put("lastname",lastname);
        }
        if(!StringUtils.isBlank(firstname)){
            newProfile.put("firstname",firstname);
        }
        if(!StringUtils.isBlank(new_pwd)
                && !StringUtils.isBlank(confirm_new_pwd)
                && new_pwd.equals(confirm_new_pwd)){
            newProfile.put("password",new_pwd);
        }
        
        if(newProfile.size() > 0) {
            updateService.updateUser(userToEdit, newProfile);
            response.sendRedirect("/user?update=success&&updateUser="+userToEdit);
        }else{
            response.sendRedirect("/user?update=fail");
        }


    }

    @Override
    public String getMapping() {
        return "/edit";
    }
}
