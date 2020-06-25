package com.sk.facility.billing.order.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class PurchaseOrder {

    @Id
    private Long id;

    private Long pipeUnitId;
}
