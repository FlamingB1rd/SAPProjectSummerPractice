package com.ivelinnikolov.ProjectSAPSummer.security;

import com.ivelinnikolov.ProjectSAPSummer.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CurstomUserDetails extends User implements UserDetails
{
    public CurstomUserDetails(final User user)
    {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> listOfAuthorities = new ArrayList<>();

        listOfAuthorities.add(new SimpleGrantedAuthority("ROLE_" + getAccountType()));

        if(getAccountType().equalsIgnoreCase("OPERATOR"))
        {
            listOfAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        }

        return listOfAuthorities;
    }

    @Override
    public String getPassword()
    {
        return super.getPassword();
    }

    @Override
    public String getUsername()
    {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
