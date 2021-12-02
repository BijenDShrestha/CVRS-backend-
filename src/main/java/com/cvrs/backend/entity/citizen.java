package com.cvrs.backend.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class citizen extends BaseEntity implements Serializable {

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
    private Integer age;

    @NotNull
    private Date dob;

    @NotNull
    @Column(unique = true)
    private String citizenship;

    @Column(name = "vaccinated_status")
    private String vaccinatedStatus;

}
