package com.sk.facility.billing.base.domain.pipe;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class PipeLength {
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PIPE_LENGTH_ID")
    private List<PipeLengthItem> pipeLengthItems = new ArrayList<>();

    public BigDecimal calculatePipeLength(PipeType pipeType, BigDecimal lineMeter){
        return pipeLengthItems.stream()
                .filter(pipeLengthItem -> pipeLengthItem.isEqualsPipeType(pipeType))
                .findFirst()
                .orElseGet(null)
                .calculatePipeLength(lineMeter);
    }
}
