package com.example.springboot.dataservice.entity;

public class Authentication {

    private String name;
    private boolean login;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "name='" + name + '\'' +
                ", login=" + login +
                ", logoff=" + logoff +
                ", timeinMilliSeconds=" + timeinMilliSeconds +
                '}';
    }
    public Authentication(){}
    public Authentication(String name, boolean login, boolean logoff, long timeinMilliSeconds) {
        this.name = name;
        this.login = login;
        this.logoff = logoff;
        this.timeinMilliSeconds = timeinMilliSeconds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isLogoff() {
        return logoff;
    }

    public void setLogoff(boolean logoff) {
        this.logoff = logoff;
    }

    public long getTimeinMilliSeconds() {
        return timeinMilliSeconds;
    }

    public void setTimeinMilliSeconds(long timeinMilliSeconds) {
        this.timeinMilliSeconds = timeinMilliSeconds;
    }

    private boolean logoff;
    private long timeinMilliSeconds;
}
