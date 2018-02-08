/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.cs.ooc.webapp;

import io.muic.cs.ooc.webapp.servlet.*;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

public class ServletRouter {

    private static final List<Class<? extends Routeable>> routeables = new ArrayList<>();
    static {
        routeables.add(LoginServlet.class);
        routeables.add(RegisterServlet.class);
        routeables.add(HomeServlet.class);
        routeables.add(LogoutServlet.class);
        routeables.add(UserServlet.class);
    }

    public void init(Context ctx) {
        for (Class<? extends Routeable> routeableClass: routeables) {
            try {
                Routeable routeable = routeableClass.newInstance();
                String name = routeable.getClass().getSimpleName();
                Tomcat.addServlet(ctx, name, (HttpServlet) routeable);
                ctx.addServletMapping(routeable.getMapping(), name);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
