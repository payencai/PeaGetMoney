package com.yichan.pea.basic.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Account {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public JSONObject getJson(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("password", password);
            jsonObject.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject;
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
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
