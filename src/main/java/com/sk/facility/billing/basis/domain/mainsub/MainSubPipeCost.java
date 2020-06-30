package com.sk.facility.billing.basis.domain.mainsub;

import com.sk.facility.billing.basis.domain.pipe.dto.MainSubPipeLine;
import com.sk.facility.billing.order.domain.OrderMainSubPipeLine;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MainSubPipeCost {
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MAIN_SUB_PIPE_COST_ID")
    private List<MainSubPipeCostItem> mainSubPipeCostItems = new ArrayList<>();

    public MainSubPipeCostItem findMainSubPipeCostItem(MainSubPipeLine mainSubPipeLine){
        return mainSubPipeCostItems.stream()
                .filter(mainSubPipeCostItem -> mainSubPipeCostItem.isSatisfied(mainSubPipeLine))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
