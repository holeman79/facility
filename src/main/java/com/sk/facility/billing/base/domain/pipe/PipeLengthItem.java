package com.sk.facility.billing.base.domain.pipe;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
public class PipeLengthItem {
    @Id
    private Long id;

    private String pipeType;

    public boolean isEqualsPipeType(PipeType pipeType){

        return this.pipeType.equals(pipeType);
    }

    public BigDecimal calculatePipeLength(BigDecimal lineMeter){
        return null;
    }
}
