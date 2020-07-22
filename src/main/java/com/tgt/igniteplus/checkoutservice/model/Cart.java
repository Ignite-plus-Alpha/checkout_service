package com.tgt.igniteplus.checkoutservice.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@Table("cart")
public class Cart {

    //table -  each row is a cart
    //cartId is created only if user does not have cartId
    //pass cartId to CartItem to view items in that cart

    @PrimaryKey
    @Column("userid")
    private String userId;
    @Column("cartid")
    private String cartId= UUID.randomUUID().toString();

    public Cart( String userId,String cartId) {
        this.userId = userId;
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", cartId='" + cartId + '\'' +
                '}';
    }
}