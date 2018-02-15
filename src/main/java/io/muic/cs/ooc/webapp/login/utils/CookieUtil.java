package io.muic.cs.ooc.webapp.login.utils;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void setCookie(HttpServletRequest req, HttpServletResponse res, String username) {
        Cookie cookie = new Cookie("userCookie", username);
        res.addCookie(cookie);
    }

    public static boolean verifyCookie(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if ("userCookie".equals(cookies[i].getName())) {
                return true;
            }
        }

        return false;
    }

    public static void removeCookie(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            cookie.setMaxAge(0); // 0 will delete cookie (According to API Docs)
            res.addCookie(cookie);
        }
    }

    public static User getUser(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if ("userCookie".equals(cookies[i].getName())) {
                String username = cookies[i].getValue();
                return MySQL.getUserbyUsername(username);

            }
        }
        return null;
    }
}
