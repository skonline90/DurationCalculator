package com.github.skonline90.model;

import java.time.Duration;

public class TypedDuration
{
    private Duration duration;
    private long quantity;
    private String quantityUnit;

    public TypedDuration(Duration duration, long quantity, String quantityUnit)
    {
        this.duration = duration;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
    }

    public Duration getDuration()
    {
        return duration;
    }

    public void setDuration(Duration duration)
    {
        this.duration = duration;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }

    public String getQuantityUnit()
    {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit)
    {
        this.quantityUnit = quantityUnit;
    }
}
