package io.muic.cs.ooc.webapp.login.services;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.utils.MyUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginService extends Service {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public LoginService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public boolean login(String username, String password) {
        if(!MyUtils.validateString(username) || !MyUtils.validateString(password)){
            return false;
        }
        boolean authorized = MySQL.authenticate(username, password);
        if(authorized){
            createCookie(username);
        }
        return authorized;
    }

    private void createCookie(String username) {
        Cookie cookie = new Cookie("userCookie", username);
        response.addCookie(cookie);
    }
}
