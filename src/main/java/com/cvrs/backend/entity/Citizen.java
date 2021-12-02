package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Citizen extends BaseEntity implements Serializable {

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    private String gender;

    @NotNull
    private Date dob;

    @NotNull
    @Column(unique = true)
    private String citizenship;

    @NotNull
    @Column(name = "phone_num", unique = true)
    private String phoneNum;

    @Column(name = "vaccinated_status")
    private String vaccinatedStatus;

    @OneToOne
    private Vaccine vaccine;

    @ManyToOne
    @NotNull
    private MedicalCondition medicalCondition;

    @ManyToOne
    @NotNull
    private Location location;

    @ManyToOne
    @NotNull
    private Occupation occupation;

    @ManyToOne
    @NotNull
    private AgeCategory ageCategory;

}
