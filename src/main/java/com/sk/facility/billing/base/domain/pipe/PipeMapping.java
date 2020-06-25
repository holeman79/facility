package com.sk.facility.billing.base.domain.pipe;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
public class PipeMapping {
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PIPE_MAPPING_ID")
    private List<PipeMappingItem> pipeMappingItems = new ArrayList<>();

    public PipeType findPipeType(WorkPipeLine workPipeLine){
        return pipeMappingItems.stream()
                .filter(pipeMappingItem -> pipeMappingItem.isSatisfied(workPipeLine))
                .findFirst()
                .orElseGet(null)
                .getPipeType();
    }
}
