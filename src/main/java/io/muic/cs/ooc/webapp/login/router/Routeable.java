package io.muic.cs.ooc.webapp.login.router;

import io.muic.cs.ooc.webapp.login.database.MySQL;

public interface Routeable {

    String getMapping();

    void setMySQL(MySQL mySQL);
}
