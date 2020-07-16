package com.ivelinnikolov.ProjectSAPSummer.models;

import com.ivelinnikolov.ProjectSAPSummer.exceptions.InvalidAccountTypeException;
import com.ivelinnikolov.ProjectSAPSummer.exceptions.InvalidEmailException;

import javax.persistence.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final String PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    public User() {}

    public User(User user)
    {
        this.id = user.getId();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.accountType = user.getAccountType();
    }

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
        /*Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches())
        {
            throw new InvalidEmailException();
        }*/
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
        /*if (accountType.equalsIgnoreCase("OPERATOR") || accountType.equalsIgnoreCase("CLIENT"))
        {
            this.accountType = accountType;
        }
        else throw new InvalidAccountTypeException();*/
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
