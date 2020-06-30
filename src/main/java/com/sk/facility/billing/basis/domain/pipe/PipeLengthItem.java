package com.sk.facility.billing.basis.domain.pipe;

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

    private BigDecimal pipeBranchQuantity;

    public boolean isEqualsPipeType(String pipeType){
        return this.pipeType.equals(pipeType);
    }

    public BigDecimal calculatePipeLength(BigDecimal lineMeter, boolean isBranch){
        return isBranch ? pipeBranchQuantity : null;
    }
}
