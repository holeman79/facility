package com.sk.facility.billing.order.domain;

import com.sk.facility.billing.basis.domain.pipe.dto.WorkPipeLine;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
public class OrderWorkPipeLine {
    @Id
    private Long id;

    private String substanceType;
    private String pipeMaterial;
    private String pipeSize;
    private String connectionType;

    private String substanceOrder;

    private BigDecimal lineMeter;

    private String valve;
    private String branchMergeLocation;



    private String pipeType;

    private String pipeTypeName;

    private BigDecimal lineQuantity;

    public boolean isEqualsSubstanceOrder(String substanceOrder){
        return StringUtils.hasText(this.substanceOrder) && Objects.equals(this.substanceOrder, substanceOrder);
    }

    public boolean isEqualsValve(String valve){
        return StringUtils.hasText(this.valve) && Objects.equals(this.valve, valve);
    }

    public WorkPipeLine convertToWorkPipeLine(){
        return new WorkPipeLine(pipeMaterial, pipeSize, connectionType);
    }

    public void setPipeTypeAndLineQuantity(String pipeType, String pipeTypeName, BigDecimal lineQuantity){

    }
}
