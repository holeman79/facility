package com.sk.facility.billing.basis.domain.pipe;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class PipeCost {
    @Id
    private Long id;

    private String pipeType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PIPE_COST_ID")
    private List<PipeCostItem> pipeCostItems = new ArrayList<>();

    public PipeCostItem findPipeCostItem(String pipeType){
        return pipeCostItems.stream()
                .filter(pipeCostItem -> pipeCostItem.isEqualsPipeType(pipeType))
                .findFirst()
                .orElseGet(null);
    }
}
