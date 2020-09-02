package com.tgt.igniteplus.checkoutservice.model;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("cart_item")
public class CartItem {

    @NotEmpty
    @PrimaryKeyColumn(name = "cart_id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String cartId;
    @NotEmpty
    @PrimaryKeyColumn(name = "item_id",ordinal = 0,type = PrimaryKeyType.CLUSTERED)
    private String itemId;
    @NotEmpty
    @PrimaryKeyColumn(name = "item_size",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private String itemSize;
    @Column("item_title")
    private String itemTitle;
    @Column("item_group")
    private String itemGroup;
    @Column("item_category")
    private String itemCategory;
    @Column("item_imageurl")
    private String itemImageURL;
    @Column("item_quantity")
    private Integer itemQuantity;
    @Column("item_price")
    private Float itemPrice;
}
