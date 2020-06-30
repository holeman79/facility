package com.sk.facility.billing.basis.domain.pipe;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
public class PipeCostItem {
    @Id
    private Long id;

    private String pipeType;

    private BigDecimal materialCost;

    private BigDecimal laborCost;

    public boolean isEqualsPipeType(String pipeType){
        return this.pipeType.equals(pipeType);
    }

    public BigDecimal calculatePipeMaterialCost(BigDecimal lineQuantity){
        return materialCost.multiply(lineQuantity);
    }

    public BigDecimal calculatePipeLaborCost(BigDecimal lineQuantity){
        return laborCost.multiply(lineQuantity);
    }
}
