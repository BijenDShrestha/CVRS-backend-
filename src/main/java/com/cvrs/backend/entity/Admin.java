package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Admin extends BaseEntity {
    @NotNull
    private String userName;

    @NotNull
    @Column(name = "phone_num")
    private String phoneNum;

    @NotNull
    private String password;
}
