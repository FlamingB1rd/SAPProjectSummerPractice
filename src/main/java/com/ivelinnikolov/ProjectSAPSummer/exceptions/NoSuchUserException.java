package com.ivelinnikolov.ProjectSAPSummer.exceptions;

public class NoSuchUserException extends RuntimeException
{
    @Override
    public String getMessage()
    {
        return "No such user found!";
    }
}
