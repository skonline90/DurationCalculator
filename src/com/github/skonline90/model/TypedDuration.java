package com.github.skonline90.model;

import java.time.Duration;

/**
 * A model class that contains specific information for a duration.
 * It consists of a quantity and a unit (f.e. seconds).
 *
 * @author skonline90
 * @version 24.12.2018
 */
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
