package com.cvrs.backend.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class citizen extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "gender")
    private String gender;

    @NotNull
    @Column(name = "age")
    private Integer age;

    @NotNull
    @Column(name = "dbo")
    private Date dob;

    @Column(name = "vaccinated_status")
    private String vaccinatedStatus;

}
