package ru.skillbox.socialnetwork.api.request;


public class SetPasswordApi {

    private String token;
    private String old;
    private String passwdord;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getPasswdord() {
        return passwdord;
    }

    public void setPasswdord(String passwdord) {
        this.passwdord = passwdord;
    }


}
