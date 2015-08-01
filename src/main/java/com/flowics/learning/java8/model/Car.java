/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.model;

/**
 * 
 * 
 * @author Marcelo
 * @since Jul 31, 2015
 */
public class Car {
    public enum Type {
        TOWNCAR, PICKUP
    }

    Person owner;
    Type type;
    String insuranceId;

    /**
     * Creates the Car.
     *
     * @param owner
     * @param type
     * @param insuranceId
     */
    public Car(Person owner, Type type, String insuranceId) {
        super();
        this.owner = owner;
        this.type = type;
        this.insuranceId = insuranceId;
    }

    /**
     * Creates the Car.
     *
     * @param owner
     * @param type
     */
    public Car(Person owner, Type type) {
        this(owner, type, null);
        owner.setCar(this);
    }
}
