package models;

import jakarta.persistence.*;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle
{
    private final static String type = "BIKE";
    public Bike()
    {
        super(type);
    }
}