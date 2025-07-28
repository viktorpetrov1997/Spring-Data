package models;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Basic
    private String type;

    protected Vehicle() {}

    protected Vehicle(String type)
    {
        this.type = type;
    }
}