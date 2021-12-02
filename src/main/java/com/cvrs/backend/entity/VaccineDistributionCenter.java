package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseentity.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class VaccineDistributionCenter extends BaseEntity  {

    @ManyToOne
    private CurrentAddress address;



}
