package com.ivelinnikolov.ProjectSAPSummer.body_request_models;

public class AddServiceToClientById
{
    private int clientId;
    private int serviceId;

    public int getClientId()
    {
        return clientId;
    }

    public void setClientId(int clientId)
    {
        this.clientId = clientId;
    }

    public int getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId = serviceId;
    }
}
