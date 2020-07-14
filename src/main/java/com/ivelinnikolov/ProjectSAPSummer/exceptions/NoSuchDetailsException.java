package com.ivelinnikolov.ProjectSAPSummer.exceptions;

public class NoSuchDetailsException extends RuntimeException
{
    @Override
    public String getMessage()
    {
        return "No such client-service details found!";
    }
}
