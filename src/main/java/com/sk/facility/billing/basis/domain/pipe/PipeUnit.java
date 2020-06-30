package com.sk.facility.billing.basis.domain.pipe;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PipeUnit {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PIPE_COST_ID")
    private PipeCost pipeCost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PIPE_LENGTH_ID")
    private PipeLength pipeLength;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PIPE_MAPPING_ID")
    private PipeMapping pipeMapping;
}
