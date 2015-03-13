package com.dragus.ellouzeandpartners.model;

/**
 * Created by T-3500 on 20/02/2015.
 *
 */
public class User {

    private String login;
    private String password;
    private int privilege;


    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public User(String login, String password, int privilege) {
        this.login = login;
        this.password = password;
        this.privilege = privilege;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
