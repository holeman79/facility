package com.sk.facility.billing.basis.domain.pipe;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class PipeType {
    @Id
    private String code;

    private String name;

    public PipeType(String code, String name){
        this.code = code;
        this.name = name;
    }
}
