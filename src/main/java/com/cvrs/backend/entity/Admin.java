package com.cvrs.backend.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import java.io.Serializable;

public class Admin extends BaseEntity implements Serializable {
    @NotNull
    private String userName;

    @NotNull
    @Column(name = "phone_num")
    private String phoneNum;

    @NotNull
    private String password;
}
