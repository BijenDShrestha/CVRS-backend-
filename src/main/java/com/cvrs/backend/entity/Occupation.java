package com.cvrs.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Occupation extends BaseEntity{
    @OneToOne
    private Citizen citizen;
}
