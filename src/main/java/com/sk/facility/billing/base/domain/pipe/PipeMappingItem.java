package com.sk.facility.billing.base.domain.pipe;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PipeMappingItem {
    @Id
    private Long id;

    private String pipeMaterial;

    private String pipeSize;

    private String connectionType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    private PipeType pipeType;

    public boolean isSatisfied(WorkPipeLine workPipeLine){
        return pipeMaterial.equals(workPipeLine.getPipeMaterial()) &&
                pipeSize.equals(workPipeLine.getPipeSize()) &&
                connectionType.equals(workPipeLine.getConnectionType());
    }
}
