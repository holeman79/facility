package com.sk.facility.billing.order.domain;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
public class OrderLineWork {
    @Id
    private Long id;

    private String workType;

    private BigDecimal materialCost;

    private BigDecimal laborCost;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_LINE_WORK_ID")
    private List<OrderWorkPipeLine> orderWorkPipeLines = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_LINE_WORK_ID")
    private List<OrderMainSubPipeLine> orderMainSubPipeLines = new ArrayList<>();

    public List<OrderWorkPipeLine> getBranches(OrderWorkPipeLine orderWorkPipeLine) {
        return orderWorkPipeLines.stream()
                .filter(o -> o.isEqualsValve(orderWorkPipeLine.getValve()))
                .sorted(Comparator.comparing(OrderWorkPipeLine::getSubstanceOrder))
                .collect(Collectors.toList());
    }

    public List<OrderWorkPipeLine> getMerges(OrderWorkPipeLine orderWorkPipeLine) {
        return orderWorkPipeLines.stream()
                .filter(o -> o.isEqualsSubstanceOrder(orderWorkPipeLine.getSubstanceOrder()))
                .sorted(Comparator.comparing(OrderWorkPipeLine::getValve))
                .collect(Collectors.toList());
    }

    private void setCost(BigDecimal materialCost, BigDecimal laborCost){
        this.materialCost = materialCost;
        this.laborCost = laborCost;
    }
}

