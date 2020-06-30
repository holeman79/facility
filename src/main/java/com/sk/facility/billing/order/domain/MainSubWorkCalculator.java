package com.sk.facility.billing.order.domain;

import com.sk.facility.billing.basis.domain.mainsub.MainSubPipeCost;
import com.sk.facility.billing.basis.domain.mainsub.MainSubPipeCostItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MainSubWorkCalculator {

    public void calculateMainSubWorkCost(OrderLineWork orderLineWork, MainSubPipeCost mainSubPipeCost){
        BigDecimal totalMaterialCost = BigDecimal.ZERO;
        BigDecimal totalLaborCost = BigDecimal.ZERO;
        for (OrderMainSubPipeLine orderMainSubPipeLine : orderLineWork.getOrderMainSubPipeLines()){
            MainSubPipeCostItem mainSubPipeCostItem = mainSubPipeCost.findMainSubPipeCostItem(orderMainSubPipeLine.converToMainSubPipeLine());

        }
    }
}
