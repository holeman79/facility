package com.sk.facility.billing.basis.domain.mainsub;

import com.sk.facility.billing.basis.domain.pipe.dto.MainSubPipeLine;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class MainSubPipeCostItem {
    @Id
    private Long id;

    public boolean isSatisfied(MainSubPipeLine mainSubPipeLine){
        return true;
    }
}
