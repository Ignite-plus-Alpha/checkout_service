package com.tgt.igniteplus.checkoutservice.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("order_details")
public class Order {

    @PrimaryKey("order_id")
    private String orderId= UUID.randomUUID().toString();
    @Column("order_items")
    private String orderItems;
    @Column("order_price")
    private Float orderPrice;
    @Column("order_quantity")
    private Integer orderQuantity;
    @Column("delivery_address")
    private String deliveryAddress;
    @Column("order_status")
    private String orderStatus;
}
