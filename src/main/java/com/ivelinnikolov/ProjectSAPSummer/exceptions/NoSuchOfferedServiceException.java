package com.ivelinnikolov.ProjectSAPSummer.exceptions;

public class NoSuchOfferedServiceException extends Exception
{
    @Override
    public String getMessage()
    {
        return "No such service found!";
    }
}
