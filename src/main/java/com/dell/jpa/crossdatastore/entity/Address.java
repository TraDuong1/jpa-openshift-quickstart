package com.dell.jpa.crossdatastore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.impetus.kundera.index.Index;
import com.impetus.kundera.index.IndexCollection;

@Entity
@IndexCollection(columns = { @Index(name = "street") })
public class Address
{
    @Id
    @Column(name = "ADDRESS_ID")
    private String addressId;

    @Column(name = "STREET")
    private String street;
    
    public void setAddressId(String id)
    {
        this.addressId = id;
    }

    public String getAddressId()
    {
        return addressId;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }
}