package com.sk.facility.billing.order.domain;

import com.sk.facility.billing.base.domain.pipe.*;
import com.sk.facility.billing.base.repository.PipeUnitRepository;
import com.sk.facility.billing.order.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OrderCalculator {

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final PipeUnitRepository pipeUnitRepository;

    public void calculateCost(Order order){
        calculateCost(order, getPurchaseOrder(order));
    }

    void calculateCost(Order order, PurchaseOrder purchaseOrder){
        calculateCost(order, getPipeUnit(purchaseOrder));
    }

    void calculateCost(Order order, PipeUnit pipeUnit){
        for(OrderLineWork orderLineWork : order.getOrderLineWorks()){
            calculateOrderLineWorkCost(orderLineWork, pipeUnit,getPipeMapping(), pipeUnit.getPipeLength(), pipeUnit.getPipeCost());
        }
    }

    private void calculateOrderLineWorkCost(OrderLineWork orderLineWork, PipeMapping pipeMapping, PipeLength pipeLength, PipeCost pipeCost){
        setPipeTypeAndQuantity(orderLineWork, pipeMapping, pipeLength);
    }

    private void setPipeTypeAndQuantity(OrderLineWork orderLineWork, PipeMapping pipeMapping, PipeLength pipeLength){
        for(OrderWorkPipeLine orderWorkPipeLine : orderLineWork.getOrderWorkPipeLines()){
            PipeType pipeType = pipeMapping.findPipeType(orderWorkPipeLine.convertToWorkPipeLine());
            BigDecimal lineQuantity = pipeLength.calculatePipeLength(pipeType, orderWorkPipeLine.getLineMeter());
            orderWorkPipeLine.setPipeTypeAndLineQuantity(pipeType.getCode(), pipeType.getName(), lineQuantity);
        }
    }

    private void setOrderLineWorkCost(OrderLineWork orderLineWorkCost, PipeCost pipeCost){
        
    }


    private PurchaseOrder getPurchaseOrder(Order order){
        return purchaseOrderRepository.findById(order.getPurchaseOrderId()).orElseThrow(IllegalArgumentException::new);
    }

    private PipeUnit getPipeUnit(PurchaseOrder purchaseOrder){
        return pipeUnitRepository.findById(purchaseOrder.getPipeUnitId()).orElseThrow(IllegalArgumentException::new);
    }


}
