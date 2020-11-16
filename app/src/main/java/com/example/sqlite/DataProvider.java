package com.example.sqlite;

public class DataProvider {
    private String name;
    private String mobile;
    private String email;

    public DataProvider(String name, String mobile, String email){
        this.setName(name);
        this.setMobile(mobile);
        this.setEmail(email);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
