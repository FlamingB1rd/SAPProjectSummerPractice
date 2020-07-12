package com.ivelinnikolov.ProjectSAPSummer.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "offered_services", schema = "mobile_services")
public class OfferedServices
{
    private int id;
    private String serivceName;
    private int offeredMinutes;
    private int offeredNumberOfMessages;
    private int offeredMbs;
    private double paymentPerMonth;

    @Id
    @Column(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "serivce_name")
    public String getSerivceName()
    {
        return serivceName;
    }

    public void setSerivceName(String serivceName)
    {
        this.serivceName = serivceName;
    }

    @Basic
    @Column(name = "offered_minutes")
    public int getOfferedMinutes()
    {
        return offeredMinutes;
    }

    public void setOfferedMinutes(int offeredMinutes)
    {
        this.offeredMinutes = offeredMinutes;
    }

    @Basic
    @Column(name = "offered_number_of_messages")
    public int getOfferedNumberOfMessages()
    {
        return offeredNumberOfMessages;
    }

    public void setOfferedNumberOfMessages(int offeredNumberOfMessages)
    {
        this.offeredNumberOfMessages = offeredNumberOfMessages;
    }

    @Basic
    @Column(name = "offered_Mbs")
    public int getOfferedMbs()
    {
        return offeredMbs;
    }

    public void setOfferedMbs(int offeredMbs)
    {
        this.offeredMbs = offeredMbs;
    }

    @Basic
    @Column(name = "payment_per_month")
    public double getPaymentPerMonth()
    {
        return paymentPerMonth;
    }

    public void setPaymentPerMonth(double paymentPerMonth)
    {
        this.paymentPerMonth = paymentPerMonth;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferedServices that = (OfferedServices) o;
        return id == that.id &&
                offeredMinutes == that.offeredMinutes &&
                offeredNumberOfMessages == that.offeredNumberOfMessages &&
                offeredMbs == that.offeredMbs &&
                Double.compare(that.paymentPerMonth, paymentPerMonth) == 0 &&
                Objects.equals(serivceName, that.serivceName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, serivceName, offeredMinutes, offeredNumberOfMessages, offeredMbs, paymentPerMonth);
    }
}
