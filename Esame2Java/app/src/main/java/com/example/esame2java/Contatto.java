package com.example.esame2java;

public class Contatto {
    private String name;
    private String tel;
    private String email;

    public Contatto(String n, String t, String e) {
        this.name = n;
        this.tel = t;
        this.email = e;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }
}
