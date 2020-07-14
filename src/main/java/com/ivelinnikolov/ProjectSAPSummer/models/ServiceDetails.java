package com.ivelinnikolov.ProjectSAPSummer.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "servicedetails", schema = "mobile_services")
@IdClass(ServicedetailsPK.class)
public class ServiceDetails
{
    private int userId;
    private int serviceId;
    private double paymentAmount;
    private String paymentDue;
    private String paymentStatus;
    private int availableMinutes;
    private int availableNumberOfMessages;
    private int availableMbs;

    @Id
    @Column(name = "user_id")
    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Id
    @Column(name = "service_id")
    public int getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "payment_amount")
    public double getPaymentAmount()
    {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount)
    {
        this.paymentAmount = paymentAmount;
    }

    @Basic
    @Column(name = "payment_due")
    public String getPaymentDue()
    {
        return paymentDue;
    }

    public void setPaymentDue(String paymentDue)
    {
        this.paymentDue = paymentDue;
    }

    @Basic
    @Column(name = "payment_status")
    public String getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    @Basic
    @Column(name = "available_minutes")
    public int getAvailableMinutes()
    {
        return availableMinutes;
    }

    public void setAvailableMinutes(int availableMinutes)
    {
        this.availableMinutes = availableMinutes;
    }

    @Basic
    @Column(name = "available_number_of_messages")
    public int getAvailableNumberOfMessages()
    {
        return availableNumberOfMessages;
    }

    public void setAvailableNumberOfMessages(int availableNumberOfMessages)
    {
        this.availableNumberOfMessages = availableNumberOfMessages;
    }

    @Basic
    @Column(name = "available_Mbs")
    public int getAvailableMbs()
    {
        return availableMbs;
    }

    public void setAvailableMbs(int availableMbs)
    {
        this.availableMbs = availableMbs;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDetails that = (ServiceDetails) o;
        return userId == that.userId &&
                serviceId == that.serviceId &&
                Double.compare(that.paymentAmount, paymentAmount) == 0 &&
                availableMinutes == that.availableMinutes &&
                availableNumberOfMessages == that.availableNumberOfMessages &&
                availableMbs == that.availableMbs &&
                Objects.equals(paymentDue, that.paymentDue) &&
                Objects.equals(paymentStatus, that.paymentStatus);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, serviceId, paymentAmount, paymentDue, paymentStatus, availableMinutes, availableNumberOfMessages, availableMbs);
    }
}
