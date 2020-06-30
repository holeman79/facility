package com.sk.facility.billing.order.domain;

import com.sk.facility.billing.basis.domain.mainsub.MainSubPipeCost;
import com.sk.facility.billing.basis.domain.pipe.*;
import com.sk.facility.billing.basis.repository.MainSubPipeCostRepository;
import com.sk.facility.billing.basis.repository.PipeUnitRepository;
import com.sk.facility.billing.order.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderCalculator {

    private final EquipmentWorkCalculator equipmentWorkCalculator;
    private final MainSubWorkCalculator mainSubWorkCalculator;

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PipeUnitRepository pipeUnitRepository;
    private final MainSubPipeCostRepository mainSubPipeCostRepository;

    public void calculateCost(Order order){
        calculateCost(order, getPipeUnitByOrder(order), getMainSubPipeCostByOrder(order));
    }

    void calculateCost(Order order, PipeUnit pipeUnit, MainSubPipeCost mainSubPipeCost){
        for(OrderLineWork orderLineWork : order.getOrderLineWorks()){
            if(Objects.equals(orderLineWork.getWorkType(), "EQUIPMENT")) equipmentWorkCalculator.calculateEquipmentWorkCost(orderLineWork, pipeUnit);
            else if(Objects.equals(orderLineWork.getWorkType(), "MAIN_SUB")) mainSubWorkCalculator.calculateMainSubWorkCost(orderLineWork, mainSubPipeCost);
        }
    }

    private PurchaseOrder getPurchaseOrder(Order order){
        return purchaseOrderRepository.findById(order.getPurchaseOrderId()).orElseThrow(IllegalArgumentException::new);
    }

    private PipeUnit getPipeUnitByOrder(Order order){
        PurchaseOrder purchaseOrder = getPurchaseOrder(order);
        return pipeUnitRepository.findById(purchaseOrder.getPipeUnitId()).orElseThrow(IllegalArgumentException::new);
    }

    private MainSubPipeCost getMainSubPipeCostByOrder(Order order) {
        PurchaseOrder purchaseOrder = getPurchaseOrder(order);
        return mainSubPipeCostRepository.findById(purchaseOrder.getMainSubPipeCostId()).orElseThrow(IllegalArgumentException::new);
    }
}
