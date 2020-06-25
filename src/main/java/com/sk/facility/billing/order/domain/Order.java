package com.sk.facility.billing.order.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Order {
    @Id
    private Long id;

    private Long purchaseOrderId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderLineWork> orderLineWorks = new ArrayList<>();
}
