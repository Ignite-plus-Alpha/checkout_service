package com.tgt.igniteplus.checkoutservice.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table("cart")
public class Cart {

    @NotEmpty
    @PrimaryKey
    @Column("user_id")
    private String userId;
    @Column("cart_id")
    private String cartId= UUID.randomUUID().toString();
    @Column("order_ids")
    private List<String> orderIds;

}