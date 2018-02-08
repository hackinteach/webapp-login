package io.muic.cs.ooc.webapp.login.database;

import io.muic.cs.ooc.webapp.login.user.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class MyUtils {
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    public static void storeConnection(ServletRequest request, Connection connection){
        request.setAttribute(ATT_NAME_CONNECTION,connection);
    }

    public static Connection getStoredConnection(ServletRequest request){
        Connection connection = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return connection;
    }

    public static void storeLoginUser(HttpSession session, User loginUser){
        session.setAttribute("loginUser",loginUser);
    }

    public static User getLoginUser(HttpSession session){
        return (User) session.getAttribute("loginUser");
    }

    public static void storedUserCookie(HttpServletResponse response, User user){
        System.out.println("Store user cookie");
        Cookie cookieUser = new Cookie(ATT_NAME_USER_NAME,user.getUsername());
        /* Save cookie for 10 mins */
        cookieUser.setMaxAge(10*60);
        response.addCookie(cookieUser);
    }

    public static String getUserCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(ATT_NAME_USER_NAME.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response){
        Cookie cookie = new Cookie(ATT_NAME_USER_NAME,null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}
