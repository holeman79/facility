package com.sk.facility.billing.basis.domain.premium;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class PremiumRateGroup {
    @Id
    private Long id;
}
