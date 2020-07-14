package com.ivelinnikolov.ProjectSAPSummer.exceptions;

public class NoSuchOfferedServiceException extends RuntimeException
{
    @Override
    public String getMessage()
    {
        return "No such service found!";
    }
}
