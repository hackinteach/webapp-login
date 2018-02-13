package io.muic.cs.ooc.webapp.login.servlet;

import io.muic.cs.ooc.webapp.login.router.Routeable;

import javax.servlet.http.HttpServlet;

public class DeleteServlet extends HttpServlet implements Routeable {
    @Override
    public String getMapping() {
        return "/";
    }
}
