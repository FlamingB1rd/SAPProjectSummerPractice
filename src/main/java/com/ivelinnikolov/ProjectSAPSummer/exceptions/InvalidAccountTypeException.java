package com.ivelinnikolov.ProjectSAPSummer.exceptions;

public class InvalidAccountTypeException extends RuntimeException
{
    @Override
    public String getMessage()
    {
        return "Invalid account type set.";
    }
}
