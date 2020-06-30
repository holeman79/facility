package com.sk.facility.billing.order.domain;

import com.sk.facility.billing.basis.domain.pipe.dto.MainSubPipeLine;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class OrderMainSubPipeLine {
    @Id
    private Long id;

    public MainSubPipeLine converToMainSubPipeLine(){
        return new MainSubPipeLine();
    }
}
