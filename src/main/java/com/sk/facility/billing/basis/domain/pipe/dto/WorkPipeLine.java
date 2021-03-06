package com.sk.facility.billing.basis.domain.pipe.dto;

import lombok.Getter;

@Getter
public class WorkPipeLine {

    private String pipeMaterial;

    private String pipeSize;

    private String connectionType;

    public WorkPipeLine(String pipeMaterial, String pipeSize, String connectionType){
        this.pipeMaterial = pipeMaterial;
        this.pipeSize = pipeSize;
        this.connectionType = connectionType;
    }
}
