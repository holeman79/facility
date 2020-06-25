package com.sk.facility.billing.order.domain;

import com.sk.facility.billing.base.domain.pipe.WorkPipeLine;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
public class OrderWorkPipeLine {
    @Id
    private Long id;

    private String pipeMaterial;

    private String pipeSize;

    private String connectionType;

    private BigDecimal lineMeter;



    private String pipeType;

    private String pipeTypeName;

    private BigDecimal lineQuantity;

    public WorkPipeLine convertToWorkPipeLine(){
        return new WorkPipeLine(pipeMaterial, pipeSize, connectionType);
    }

    public void setPipeTypeAndLineQuantity(String pipeType, String pipeTypeName, BigDecimal lineQuantity){

    }
}
