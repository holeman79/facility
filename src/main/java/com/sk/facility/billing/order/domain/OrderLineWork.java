package com.sk.facility.billing.order.domain;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class OrderLineWork {
    @Id
    private Long id;

    private BigDecimal materialCost;

    private BigDecimal laborCost;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_LINE_WORK_ID")
    private List<OrderWorkPipeLine> orderWorkPipeLines = new ArrayList<>();

    private void setCost(BigDecimal materialCost, BigDecimal laborCost){
        this.materialCost = materialCost;
        this.laborCost = laborCost;
    }
}

