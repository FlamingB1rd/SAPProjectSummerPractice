package com.ivelinnikolov.ProjectSAPSummer.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ServicedetailsPK implements Serializable
{
    private int userId;
    private int serviceId;

    @Column(name = "user_id")
    @Id
    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Column(name = "service_id")
    @Id
    public int getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicedetailsPK that = (ServicedetailsPK) o;
        return userId == that.userId &&
                serviceId == that.serviceId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, serviceId);
    }
}
