package com.sk.facility.billing.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WorkPipeLineByType {
    private String pipeType;

    private String pipeTypeName;

    private BigDecimal sumLineQuantity;
}
