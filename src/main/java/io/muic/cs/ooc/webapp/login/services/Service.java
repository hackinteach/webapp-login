package io.muic.cs.ooc.webapp.login.services;

import io.muic.cs.ooc.webapp.login.database.MySQL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Service {

    protected MySQL mySQL = new MySQL();
    private HttpServletRequest request;
    private HttpServletResponse response;


    abstract public void error(String msg, String path);

}
