package com.codegym.usermanager.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class User  {
    protected int id;
    protected String name;
    protected String email;
    protected int idcountry;
    protected String password;
    protected  String urlImage;

    public User(int id, String name, String email, int idcountry, String password) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.idcountry = idcountry;
        this.password = password;
    }
    public String getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    public User() {}
    public User(int id, String name, String email, int idcountry, String password, String urlImage) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.idcountry = idcountry;
        this.password = password;
        this.urlImage = urlImage;
    }

    public User(String name, String email, int idcountry, String password) {
        super();
        this.name = name;
        this.email = email;
        this.idcountry = idcountry;
        this.password = password;
    }

    public User(int id, String name, String email, int idcountry) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.idcountry = idcountry;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty (message = "Name not empty")
    @Length(min = 3, max = 10 , message = "Length of Name form 3 - 10 character ")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @Email(message = "Email format not right")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdcountry() {
        return idcountry;
    }

    public void setIdcountry(int idcountry) {
        this.idcountry = idcountry;
    }

    @NotEmpty
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "Format password not right")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
