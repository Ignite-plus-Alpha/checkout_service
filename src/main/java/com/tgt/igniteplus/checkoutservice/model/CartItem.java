package com.tgt.igniteplus.checkoutservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("cart_item")
public class CartItem {

    @PrimaryKeyColumn(name = "cart_id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String cartId;
    @PrimaryKeyColumn(name = "item_id",ordinal = 0,type = PrimaryKeyType.CLUSTERED)
    private String itemId;
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
