package io.muic.cs.ooc.webapp.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * users table
 * stores login credentials
 */

@Entity
@Table(name = "credentials")
public class User extends AbstractEntity implements Serializable {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.getId() +
                ", username=" + this.getUsername() +
                ", password=" + this.getPassword() +
                '}';
    }
}
