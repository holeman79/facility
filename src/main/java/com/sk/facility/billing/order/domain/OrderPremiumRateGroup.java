package com.sk.facility.billing.order.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class OrderPremiumRateGroup {
    @Id
    private Long id;
}
