package com.dell.jpa.crossdatastore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.impetus.kundera.index.Index;
import com.impetus.kundera.index.IndexCollection;

@Entity
@IndexCollection(columns = { @Index(name = "personName") })
public class User {
    @Id
    @Column(name = "PERSON_ID")
    private int personId;

    @Column(name = "PERSON_NAME")
    private String personName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    public int getPersonId()
    {
        return personId;
    }
    
    public void setPersonId(int personId)
    {
        this.personId = personId;
    }


    public String getPersonName()
    {
        return personName;
    }

    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

}
