package com.ivelinnikolov.ProjectSAPSummer.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "mobile_services")
public class User
{
    private int id;
    private String fname;
    private String lname;
    private String username;
    private String password;
    private String email;
    private String accountType;

    @Id
    @Column(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "fname")
    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    @Basic
    @Column(name = "lname")
    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    @Basic
    @Column(name = "username")
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Basic
    @Column(name = "account_type")
    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id &&
                Objects.equals(fname, that.fname) &&
                Objects.equals(lname, that.lname) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, fname, lname, username, password, email, accountType);
    }
}
