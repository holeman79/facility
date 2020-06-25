package com.sk.facility.billing.base.domain.pipe;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class PipeType {
    @Id
    private String code;

    private String name;
}
