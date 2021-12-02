package com.cvrs.backend.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AgeCategory extends BaseEntity implements Serializable {

    private String range;
}
