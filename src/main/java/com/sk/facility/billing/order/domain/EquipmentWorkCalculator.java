package com.sk.facility.billing.order.domain;

import com.sk.facility.billing.basis.domain.pipe.*;
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
public class EquipmentWorkCalculator {

    public void calculateEquipmentWorkCost(OrderLineWork orderLineWork, PipeUnit pipeUnit){
        // Type과 수량 산출함수
        setPipeTypeAndQuantity(orderLineWork, pipeUnit.getPipeMapping(), pipeUnit.getPipeLength());
        // cost 산출 함수
        setEquipmentWorkCost(orderLineWork, pipeUnit.getPipeCost());
    }

    private void setPipeTypeAndQuantity(OrderLineWork orderLineWork, PipeMapping pipeMapping, PipeLength pipeLength){
        for(OrderWorkPipeLine orderWorkPipeLine : orderLineWork.getOrderWorkPipeLines()){
            setPipeTypeAndQuantity(orderWorkPipeLine, orderLineWork, pipeMapping, pipeLength);
        }
    }

    private void setPipeTypeAndQuantity(OrderWorkPipeLine orderWorkPipeLine, OrderLineWork orderLineWork, PipeMapping pipeMapping, PipeLength pipeLength){
        PipeType pipeType = pipeMapping.findPipeType(orderWorkPipeLine.convertToWorkPipeLine());
        if(pipeType == null) return;
        List<OrderWorkPipeLine> branches = orderLineWork.getBranches(orderWorkPipeLine);
        List<OrderWorkPipeLine> merges = orderLineWork.getMerges(orderWorkPipeLine);
        String branchMergeLocation = orderWorkPipeLine.getBranchMergeLocation();
        boolean isBranch = false;

        if(!branches.isEmpty() && branches.size() > 1
                && branchMergeLocation.equals("EQ") && !Objects.equals(branches.get(0).getId(), orderWorkPipeLine.getId())){
            pipeType = new PipeType(branches.get(0).getPipeType(), branches.get(0).getPipeTypeName());
            isBranch = true;
        }else if(!merges.isEmpty() && merges.size() > 1){
            if(!branchMergeLocation.equals("EQ")){
                OrderWorkPipeLine mergeFirst = merges.get(0);
                if(!mergeFirst.getId().equals(orderWorkPipeLine.getId())){
                    orderWorkPipeLine.setPipeTypeAndLineQuantity(pipeType.getCode(), pipeType.getName(), BigDecimal.ZERO);
                    return;
                }
            }
        }
        BigDecimal lineQuantity = getLineQuantity(orderWorkPipeLine, pipeLength, pipeType, isBranch);
        orderWorkPipeLine.setPipeTypeAndLineQuantity(pipeType.getCode(), pipeType.getName(), lineQuantity);
    }

    private BigDecimal getLineQuantity(OrderWorkPipeLine orderWorkPipeLine, PipeLength pipeLength, PipeType pipeType, boolean isBranch) {
        return pipeLength.calculatePipeLength(pipeType.getCode(), orderWorkPipeLine.getLineMeter(), isBranch);
    }

    private void setEquipmentWorkCost(OrderLineWork orderLineWork, PipeCost pipeCost){
        BigDecimal totalMaterialCost = BigDecimal.ZERO;
        BigDecimal totalLaborCost = BigDecimal.ZERO;

        Map<String, List<OrderWorkPipeLine>> lineQuantityByPipeType = orderLineWork.getOrderWorkPipeLines().stream()
                .collect(Collectors.groupingBy(OrderWorkPipeLine::getPipeType));

        for (String pipeType : lineQuantityByPipeType.keySet()) {
            PipeCostItem pipeCostItem = pipeCost.findPipeCostItem(pipeType);
            BigDecimal lineQuantity = lineQuantityByPipeType.get(pipeType).stream()
                    .map(orderWorkPipeLine -> orderWorkPipeLine.getLineQuantity())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            totalMaterialCost.add(pipeCostItem.calculatePipeMaterialCost(lineQuantity));
            totalLaborCost.add(pipeCostItem.calculatePipeLaborCost(lineQuantity));
        }
    }

    //    private void setPipeTypeAndQuantity(OrderWorkPipeLine orderWorkPipeLine, OrderLineWork orderLineWork, PipeMapping pipeMapping, PipeLength pipeLength){
//        PipeType pipeType = pipeMapping.findPipeType(orderWorkPipeLine.convertToWorkPipeLine());
//        if(pipeType == null) return;
//
//        List<OrderWorkPipeLine> merges = orderLineWork.getMerges(orderWorkPipeLine);
//        BigDecimal lineQuantity = BigDecimal.ZERO;
//
//        boolean isBranch = isBranch(orderWorkPipeLine, orderLineWork, pipeType);
//
//
////        }else if(!merges.isEmpty() && merges.size() > 1){
////            if(!branchMergeLocation.equals("EQ")){
////                OrderWorkPipeLine mergeFirst = merges.get(0);
////                if(!mergeFirst.getId().equals(orderWorkPipeLine.getId())){
////                    orderWorkPipeLine.setPipeTypeAndLineQuantity(pipeType.getCode(), pipeType.getName(), BigDecimal.ZERO);
////                    return;
////                }
////            }
////        }
////        lineQuantity = getLineQuantity(orderWorkPipeLine, pipeLength, pipeType, isBranch);
////        orderWorkPipeLine.setPipeTypeAndLineQuantity(pipeType.getCode(), pipeType.getName(), lineQuantity);
//    }
//
//    private boolean isBranch(OrderWorkPipeLine orderWorkPipeLine, OrderLineWork orderLineWork, PipeType pipeType){
//        List<OrderWorkPipeLine> branches = orderLineWork.getBranches(orderWorkPipeLine);
//        String branchMergeLocation = orderWorkPipeLine.getBranchMergeLocation();
//        if(!branches.isEmpty() && branches.size() > 1
//                && branchMergeLocation.equals("EQ") && !Objects.equals(branches.get(0).getId(), orderWorkPipeLine.getId())) {
//            pipeType = new PipeType(branches.get(0).getPipeType(), branches.get(0).getPipeTypeName());
//            return true;
//        }
//        return false;
//    }


}
