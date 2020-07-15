package com.ivelinnikolov.ProjectSAPSummer.exceptions;

public class InvalidEmailException extends RuntimeException
{
    @Override
    public String getMessage()
    {
        return "Invalid email set.";
    }
}
